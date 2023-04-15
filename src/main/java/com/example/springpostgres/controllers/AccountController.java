package com.example.springpostgres.controllers;

import com.example.springpostgres.models.Account;
import com.example.springpostgres.models.Person;
import com.example.springpostgres.repositories.AccountRepository;
import com.example.springpostgres.utils.MyUserDetailsService;
import com.example.springpostgres.utils.JwtTokenUtil;
import com.example.springpostgres.utils.PersonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("api/account")
public class AccountController {
    AccountRepository accountRepository;
    AuthenticationManager authenticationManager;
    MyUserDetailsService myUserDetailsService;
    JwtTokenUtil jwtTokenUtil;

    PasswordEncoder passwordEncoder;

    @Autowired
    public AccountController(
            AccountRepository accountRepository,
            AuthenticationManager authenticationManager,
            MyUserDetailsService myUserDetailsService,
            JwtTokenUtil jwtTokenUtil,
            PasswordEncoder passwordEncoder
    ) {
        this.accountRepository = accountRepository;
        this.authenticationManager = authenticationManager;
        this.myUserDetailsService = myUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("sign-in")
    public ResponseEntity<?> authenticate(@RequestBody Account account) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account.getEmail(), account.getPassword())
        );
        Account user = accountRepository.findAccountByEmail(account.getEmail());
        String token = jwtTokenUtil.generateToken(user);
        System.out.println(token);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("sign-up")
    public ResponseEntity<?> signUp(@RequestBody Account account) {
        Account foundAccount = accountRepository.findAccountByEmail(account.getEmail());
        if (foundAccount != null) {
            return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        try {
            accountRepository.save(account);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Account user = accountRepository.findAccountByEmail(account.getEmail());
        String token = jwtTokenUtil.generateToken(user);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Account account) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account old = accountRepository.findAccountByEmail(currentUser.getUsername());
        if (account.getEmail() != null && !Objects.equals(account.getEmail(), old.getEmail())) {
            if (accountRepository.findAccountByEmail(account.getEmail()) != null) {
                return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
            }
            old.setPassword(account.getPassword());
        }
        if (account.getPassword() != null) {
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            old.setPassword(account.getPassword());
        }
        Person oldPerson = old.getPerson();
        Person newPerson = account.getPerson();
        PersonUtil.updatePerson(oldPerson, newPerson);
        old.setRole(account.getRole());

        accountRepository.save(old);
        Account user = accountRepository.findAccountByEmail(old.getEmail());
        String token = jwtTokenUtil.generateToken(user);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
