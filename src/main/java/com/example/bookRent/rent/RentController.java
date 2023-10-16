package com.example.bookRent.rent;


import com.example.bookRent.book.Book;
import com.example.bookRent.book.BookRepository;
import com.example.bookRent.book.BookService;
import com.example.bookRent.user.SiteUser;
import com.example.bookRent.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class RentController {


    final private RnetService rentService;

    final private BookService bookService;

    final private UserService userService;
    final private BookRepository bookRepository;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/rent/{id}")
    public String reviewCreate(@PathVariable("id") int id, Model model , Principal principal){

        Book book = this.bookService.getBook(id);


        SiteUser siteUser = this.userService.getUser(principal.getName());

        this.rentService.createRent(book,siteUser);


        return "redirect:/";


    }
}
