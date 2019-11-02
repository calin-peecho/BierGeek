package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

//    @Query("SELECT u.id FROM User u where u.username = :name")
//    long findIdByUsername(@Param("name") String name);

    User getByUsername(String name);

}