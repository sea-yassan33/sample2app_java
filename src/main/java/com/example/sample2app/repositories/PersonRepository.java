package com.example.sample2app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sample2app.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
}
