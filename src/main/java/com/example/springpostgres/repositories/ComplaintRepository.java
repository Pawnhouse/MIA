package com.example.springpostgres.repositories;

import com.example.springpostgres.models.Complaint;
import org.springframework.data.repository.CrudRepository;

public interface ComplaintRepository extends CrudRepository<Complaint, Integer> {
}
