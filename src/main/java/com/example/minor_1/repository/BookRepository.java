package com.example.minor_1.repository;

import com.example.minor_1.models.Book;
import com.example.minor_1.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book> findByName(String name);

    List<Book> findByAuthor_Name(String authorName);

    List<Book> findByGenre(Genre genre);


}
