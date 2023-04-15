package com.example.springpostgres.controllers;

import com.example.springpostgres.models.Type;
import com.example.springpostgres.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/type")
public class TypeController {
    private final TypeRepository typeRepository;

    @Autowired
    public TypeController(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @GetMapping
    public Iterable<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    @PostMapping
    public Type createEmployee(@RequestBody Type type) {
        return typeRepository.save(type);
    }
}
