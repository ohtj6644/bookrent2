package com.example.bookRent.rent;

import com.example.bookRent.DataNotFoundException;
import com.example.bookRent.book.Book;
import com.example.bookRent.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    public Page<Rent> getMyList(int page, SiteUser siteUser) {
        List<Sort.Order> sorts= new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page,20,Sort.by(sorts));
        return this.rentRepository.findByBorrower(pageable,siteUser);
    }

    public Book returnRent(int id) {
        Rent rent = this.rentRepository.getReferenceById(id);
        rent.setState(false);
        this.rentRepository.save(rent);


        Optional<Rent> article = this.rentRepository.findById(id);
        if (article.isPresent()) {
            Rent rent1 = article.get();

            return rent1.getArticle();
        } else {
            throw new DataNotFoundException("freeNotice not found");
        }

    }

    public Page<Rent> getHistoryList(Book book,int page) {

        List<Sort.Order> sorts= new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page,20,Sort.by(sorts));
        return this.rentRepository.findByArticle(pageable,book);
    }
}
