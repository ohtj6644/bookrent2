package com.example.bookRent.book;


import com.example.bookRent.user.User;
import com.example.bookRent.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class BookController {

    final private  BookService bookService;

    final private UserService userService;


    @GetMapping("/create")
    public String newsCreate(BookForm BookForm ){
        return "book_form";
    }
    @PostMapping("/create")
    public String articleCreate(@Valid BookForm BookForm ,
                                BindingResult bindingResult
                                ) throws Exception {
        if (bindingResult.hasErrors()) {
            return "book_form";
        }

        this.bookService.create(BookForm );
        return "redirect:/";
    }


}
