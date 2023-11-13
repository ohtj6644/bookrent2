package com.example.bookRent.book;


import com.example.bookRent.rent.Rent;
import com.example.bookRent.rent.RentService;
import com.example.bookRent.user.SiteUser;
import com.example.bookRent.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class BookController {

    final private  BookService bookService;

    final private UserService userService;

    final private RentService rentService;


    @GetMapping("/")
    public String mainpage(Model model , @RequestParam(value = "page",defaultValue = "0")int page) {

        Page<Book> paging = this.bookService.getList(page);

        model.addAttribute("paging",paging);

        return "main";
    }

    @GetMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String newsCreate(BookForm BookForm ){
        return "book_form";
    }


    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String articleCreate(@Valid BookForm BookForm ,
                                BindingResult bindingResult
                                ,Principal principal) throws Exception {
        if (bindingResult.hasErrors()) {
            return "book_form";
        }
        SiteUser siteUser = this.userService.getUser(principal.getName());

        this.bookService.create(BookForm,siteUser);
        return "redirect:/";
    }


    @GetMapping("/book/history/{id}")
    public String bookhistory(@PathVariable("id") int id, Model model,@RequestParam(value = "page",defaultValue = "0")int page){

        Book book=this.bookService.getBook(id);

        Page<Rent> paging = this.rentService.getHistoryList(book,page);

        model.addAttribute("paging",paging);
        return "history";
    }



    @PreAuthorize("isAuthenticated()")
    @GetMapping("/book/modify/{id}")
    public String questionModify(BookForm bookForm, @PathVariable("id") Integer id, Principal principal) {
        Book book = this.bookService.getBookModify(id);
        if(!book.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        bookForm.setName(book.getName());
        bookForm.setWriter(book.getWriter());
        return "book_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/book/modify/{id}")
    public String questionModify(@Valid BookForm bookForm, BindingResult bindingResult,
                                 Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        Book book= this.bookService.getBookModify(id);
        if (!book.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.bookService.modify(book, bookForm.getName(), bookForm.getWriter());
        return "redirect:/";
    }



}
