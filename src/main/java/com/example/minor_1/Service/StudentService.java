package com.example.minor_1.Service;

import com.example.minor_1.models.Student;
import jakarta.validation.Valid;
import com.example.minor_1.request.StudentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.minor_1.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void create(@Valid StudentCreateRequest studentCreateRequest) {

        Student student = studentCreateRequest.to();
        studentRepository.save(student);
    }

    public Student findStudentByStudentId(int studentId) {

        return studentRepository.findById(studentId).orElse(null);
    }
}
