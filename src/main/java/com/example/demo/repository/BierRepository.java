package com.example.demo.repository;

import com.example.demo.entity.Bier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BierRepository extends CrudRepository<Bier, Long> {

    long countByUser_Id(long id);
}
