package com.example.bookRent.user;

import com.example.bookRent.user.User;
import com.example.bookRent.user.UserCreateForm;
import com.example.bookRent.user.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController1 {


    final private UserService userService;

    @GetMapping("/api/test")
    public String getApiTest(){
        return "{\"result\":\"ok\"}";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm) {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup";
        }

        userService.create(userCreateForm.getUsername(),userCreateForm.getPassword1() );

        return "redirect:/";
    }


    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password) {
        if (userService.authenticateUser(username, password)) {
            session.setAttribute("loggedIn", true);

            User user = userService.getUser(username);
            session.setAttribute("user", user);

            return "redirect:/";
        } else {
            return "login";
        }
    }

    @GetMapping("/logout")
    public String Logout(HttpSession session) {
        session.removeAttribute("loggedIn");
        return "redirect:/";
    }


}
