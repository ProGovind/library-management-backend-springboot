package com.example.minor_1.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Enumerated(value = STRING)
    private AccountStatus accountStatus;

    @Column(unique = true)
    private String email;

    private String address;
    @Column(unique = true , nullable = false)
    private String contact;
    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;

    @OneToMany(mappedBy = "student")
    private List<Book> bookList;

    @OneToMany(mappedBy = "student")
    private List<Transaction>  transactionList;


}
