package bookstoreApp.controller;

import bookstoreApp.dto.UserDto;
import bookstoreApp.service.user.AuthenticationException;
import bookstoreApp.service.user.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/login")
public class LoginController { ;
    AuthenticationService authenticationService;

    @Autowired
    public LoginController( AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @GetMapping()
    public String userForm(Model model) {
        model.addAttribute(new UserDto());
        return "login";
    }

    @PostMapping(params = "registerBtn")
    public String userRegister(@ModelAttribute UserDto userDto) {
        authenticationService.register(userDto);
        return "login";
    }

    @PostMapping(params = "loginBtn")
    public String userLogin(@ModelAttribute UserDto userDto) {
        String nextPage="login";
        try {
            UserDto backUserDto=authenticationService.login(userDto);
            nextPage =decideBasedOnRole(backUserDto);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return nextPage;
    }

    private String decideBasedOnRole(UserDto userDto){
        switch(userDto.role){
            case "employee": return "redirect:/emplMenu";
            case "administrator": return "redirect:/adminMenu";
            default: return "login";
        }
    }


}
