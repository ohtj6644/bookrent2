package com.example.bookRent.rent;

import com.example.bookRent.book.Book;
import com.example.bookRent.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class RnetService {

    final private RentRepository rentRepository;



    public void createRent(Book book, SiteUser siteUser) {
        Rent rent = new Rent();
        rent.setArticle(book);
        rent.setBorrower(siteUser);
        rent.setRentDate(LocalDate.now());
        rent.setState(true);

        this.rentRepository.save(rent);


    }
}
