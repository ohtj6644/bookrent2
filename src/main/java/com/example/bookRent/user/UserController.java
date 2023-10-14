import com.example.bookRent.user.UserCreateForm;
import com.example.bookRent.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {


    final private UserService userService;

    @RequestMapping(value = "/api/test",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String getApiTest(){
        return "{\"result\":\"ok\"}";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() {
        return "login";
    }


    @RequestMapping(value = "/signup",method = RequestMethod.GET)
    public String signup(UserCreateForm userCreateForm) {
        return "signup";
    }

    @RequestMapping(value = "/signup",method = RequestMethod.POST)
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



}
