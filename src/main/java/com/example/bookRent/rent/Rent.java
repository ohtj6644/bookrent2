package com.example.bookRent.rent;


import com.example.bookRent.book.Book;
import com.example.bookRent.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrower_id")
    private User borrower;


    @ManyToOne
    private Book article;

    private LocalDate rentDate;

    private boolean state;

}
