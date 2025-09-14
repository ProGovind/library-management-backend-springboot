package com.example.minor_1.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String country;
    private int age;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<Book> booklist;
    @CreationTimestamp
    private Date addedOn;
}
