package com.example.bookRent.book;


import com.example.bookRent.rent.Rent;
import com.example.bookRent.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String writer;

    private LocalDate createDate;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Rent> rentList;

    private boolean state;

    @ManyToOne
    private SiteUser registrant;
}
