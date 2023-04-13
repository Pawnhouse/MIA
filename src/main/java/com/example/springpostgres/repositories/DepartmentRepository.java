package com.example.springpostgres.repositories;

import com.example.springpostgres.models.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {
}
