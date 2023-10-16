package com.example.bookRent.rent;

import com.example.bookRent.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface RentRepository extends JpaRepository<Rent, Integer> {
}
