package com.example.bookRent.rent;


import com.example.bookRent.book.Book;
import com.example.bookRent.book.BookRepository;
import com.example.bookRent.book.BookService;
import com.example.bookRent.user.SiteUser;
import com.example.bookRent.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/myrent")
    public String myrentList(Model model , Principal principal,@RequestParam(value = "page",defaultValue = "0")int page){
        SiteUser siteUser = this.userService.getUser(principal.getName());

        Page<Rent> paging = this.rentService.getMyList(page,siteUser);

        model.addAttribute("paging",paging);

        return "my_rent_list";

    }

    @GetMapping("/rent/return/{id}")
    public String reviewReturn(@PathVariable("id") int id, Model model , Principal principal){

        Book book = this.rentService.returnRent(id);
        this.bookService.returnBook(book);

        return "redirect:/myrent";
    }

}
