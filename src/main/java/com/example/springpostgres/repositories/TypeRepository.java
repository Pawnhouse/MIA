package com.example.springpostgres.repositories;

import com.example.springpostgres.models.Type;
import org.springframework.data.repository.CrudRepository;

public interface TypeRepository extends CrudRepository<Type, Integer> {
}
