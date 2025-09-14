package com.example.minor_1.repository;

import com.example.minor_1.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {

    Author findByEmail(String email);
}
