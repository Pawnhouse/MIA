package com.example.springpostgres.repositories;

import com.example.springpostgres.models.Officer;
import org.springframework.data.repository.CrudRepository;

public interface OfficerRepository extends CrudRepository<Officer, Integer> {
}
