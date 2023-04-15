package com.example.springpostgres.controllers;

import com.example.springpostgres.models.Crime;
import com.example.springpostgres.models.Criminal;
import com.example.springpostgres.models.Person;
import com.example.springpostgres.repositories.CrimeRepository;
import com.example.springpostgres.repositories.CriminalRepository;
import com.example.springpostgres.utils.PersonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("api/criminal")
public class CriminalController {
    private final CrimeRepository crimeRepository;
    private final CriminalRepository criminalRepository;

    @Autowired
    public CriminalController(CrimeRepository crimeRepository, CriminalRepository criminalRepository) {
        this.crimeRepository = crimeRepository;
        this.criminalRepository = criminalRepository;
    }

    @GetMapping
    public Iterable<Criminal> getAllCriminals() {
        return criminalRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Criminal criminal) {
        criminal = criminalRepository.save(criminal);
        return new ResponseEntity<>(criminal.getId(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Criminal criminal) {
        Criminal old = criminalRepository.findById(criminal.getId()).orElseThrow();
        old.setBiography(criminal.getBiography());
        old.setNickname(criminal.getNickname());
        Person oldPerson = old.getPerson();
        Person newPerson = criminal.getPerson();
        PersonUtil.updatePerson(oldPerson, newPerson);
        criminalRepository.save(old);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("add-to-crime")
    public ResponseEntity<?> setCriminals(@RequestBody Map<String, Object> body) {
        ArrayList<Object> criminalIdObjectList = new ArrayList<>((Collection<?>) body.get("criminalIds"));
        ArrayList<Integer> criminalIds = new ArrayList<>();
        for (Object o : criminalIdObjectList) {
            criminalIds.add((Integer) o);
        }
        int crimeId = (int) body.get("crimeId");
        HashSet<Criminal> criminals = new HashSet<>(
                (Collection<Criminal>) criminalRepository.findAllById(criminalIds)
        );
        System.out.println(criminals);
        Crime crime = crimeRepository.findById(crimeId).orElseThrow();
        crime.setCriminals(criminals);
        crimeRepository.save(crime);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
