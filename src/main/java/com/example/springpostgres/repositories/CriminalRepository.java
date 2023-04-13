package com.example.springpostgres.repositories;

import com.example.springpostgres.models.Criminal;
import org.springframework.data.repository.CrudRepository;

public interface CriminalRepository extends CrudRepository<Criminal, Integer> {
}
