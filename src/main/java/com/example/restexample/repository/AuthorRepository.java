package com.example.restexample.repository;

import com.example.restexample.model.Author;
import com.example.restexample.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
