package com.example.bookRent.rent;

import com.example.bookRent.book.Book;
import com.example.bookRent.user.SiteUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface RentRepository extends JpaRepository<Rent, Integer> {
    Page<Rent> findByBorrower(Pageable pageable, SiteUser siteUser);

    Page<Rent> findByArticle(Pageable pageable, Book book);
}
