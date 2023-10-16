package com.example.bookRent.book;


import com.example.bookRent.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {


    final private BookRepository bookRepository;


    public void create(BookForm bookForm) {
        Book book = new Book();
        book.setName(bookForm.getName());
        book.setWriter(bookForm.getWriter());
        book.setCreateDate(LocalDate.now());
        book.setState(true);
        this.bookRepository.save(book);
    }

    public Page<Book> getList(int page) {
        List<Sort.Order> sorts= new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page,20,Sort.by(sorts));
        return this.bookRepository.findAll(pageable);

    }

    public Book getBook(int id) {
        Book book = this.bookRepository.getReferenceById(id);
        book.setState(false);
        this.bookRepository.save(book);


        Optional<Book> article = this.bookRepository.findById(id);
        if (article.isPresent()) {
            return article.get();
        } else {
            throw new DataNotFoundException("freeNotice not found");
        }

    }
}
