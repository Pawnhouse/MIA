package com.example.springpostgres.controllers;

import com.example.springpostgres.models.Officer;
import com.example.springpostgres.repositories.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/officer")
public class OfficerController {

    private final OfficerRepository officerRepository;
    @Autowired
    public OfficerController(OfficerRepository officerRepository) {
        this.officerRepository = officerRepository;
    }

    @GetMapping
    public Iterable<Officer> getAllOfficers() {
        return officerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOfficerById(@PathVariable String id){
        try{
            Officer officer = officerRepository.findById(Integer.parseInt(id)).orElseThrow();
            return new ResponseEntity<>(officer, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
