package com.example.springpostgres.repositories;

import com.example.springpostgres.models.Crime;
import org.springframework.data.repository.CrudRepository;

public interface CrimeRepository extends CrudRepository<Crime, Integer> {
}
