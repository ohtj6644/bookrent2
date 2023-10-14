package com.example.bookRent.book;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BookService {


    final private BookRepository bookRepository;


    public void create(BookForm bookForm) {
        Book book = new Book();
        book.setName(bookForm.getName());
        book.setWriter(bookForm.getWriter());
        book.setCreateDate(LocalDate.now());
        this.bookRepository.save(book);
    }
}
