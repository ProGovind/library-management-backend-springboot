package com.example.minor_1.repository;

import com.example.minor_1.models.Book;
import com.example.minor_1.models.Student;
import com.example.minor_1.models.Transaction;
import com.example.minor_1.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    Transaction findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(
            Book book, Student student, TransactionType transactionType
    );
}
