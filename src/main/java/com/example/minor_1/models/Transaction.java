package com.example.minor_1.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double payment;

   @Enumerated
    private TransactionType transactionType;

    @CreationTimestamp
   private Date transactionDate;

   @ManyToOne
   @JoinColumn
   private Book book;

    private String externalTxnId;

   @ManyToOne
   @JoinColumn
   private Student student;
}
