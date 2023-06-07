package com.example.sample2app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sample2app.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
  public Optional<Message> findById(Long id);
}

