package com.example.database_project;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;


@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Publisher() {
    }

    @OneToOne(mappedBy = "publisher")
    //@JsonManagedReference
    @JsonBackReference
    private Book book;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
}

