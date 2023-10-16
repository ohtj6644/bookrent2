package com.example.bookRent.book;


import com.example.bookRent.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BookController {

    final private  BookService bookService;

    final private UserService userService;


    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
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
                                ) throws Exception {
        if (bindingResult.hasErrors()) {
            return "book_form";
        }

        this.bookService.create(BookForm );
        return "redirect:/";
    }


}
