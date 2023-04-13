package com.example.springpostgres.controllers;

import com.example.springpostgres.models.Crime;
import com.example.springpostgres.models.Type;
import com.example.springpostgres.repositories.CrimeRepository;
import com.example.springpostgres.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/crime")
public class CrimeController {
    private final CrimeRepository crimeRepository;
    private final TypeRepository typeRepository;

    @Autowired
    public CrimeController(CrimeRepository crimeRepository, TypeRepository typeRepository) {
        this.crimeRepository = crimeRepository;
        this.typeRepository = typeRepository;
    }

    @GetMapping
    public Iterable<Crime> getAllCrimes() {
        return crimeRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Crime crime) {
        Type type = typeRepository.findById(crime.getType().getId()).orElseThrow();
        crime.setType(type);
        crimeRepository.save(crime);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
