package com.example.minor_1.controllers;

import com.example.minor_1.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.minor_1.request.StudentCreateRequest;


@RestController
public class studentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public void createStudent(@Valid @RequestBody StudentCreateRequest studentCreateRequest)
    {
        studentService.create(studentCreateRequest);
    }
}
