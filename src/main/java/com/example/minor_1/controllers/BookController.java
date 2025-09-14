package com.example.minor_1.controllers;

import com.example.minor_1.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.minor_1.request.BookCreateRequest;

@RestController
public class BookController {

  @Autowired
  BookService bookService;

  @PostMapping("/book")
    public void createBook(@Valid @RequestBody BookCreateRequest bookCreateRequest)
  {
      bookService.createOrUpdate(bookCreateRequest);
  }



}
