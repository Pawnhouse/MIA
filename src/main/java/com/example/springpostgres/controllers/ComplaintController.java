package com.example.springpostgres.controllers;

import com.example.springpostgres.models.Account;
import com.example.springpostgres.models.Complaint;
import com.example.springpostgres.repositories.AccountRepository;
import com.example.springpostgres.repositories.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/complaint")
public class ComplaintController {
    private final ComplaintRepository complaintRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public ComplaintController(ComplaintRepository complaintRepository, AccountRepository accountRepository) {
        this.complaintRepository = complaintRepository;
        this.accountRepository = accountRepository;
    }


    @GetMapping
    public Iterable<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> send(@RequestBody Complaint complaint) {
        User account = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account sender = accountRepository.findAccountByEmail(account.getUsername());
        complaint.setSender(sender);
        complaintRepository.save(complaint);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        complaintRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
