package bookstoreApp.controller;

import bookstoreApp.constants.ApplicationConstants;
import bookstoreApp.dto.UserDto;
import bookstoreApp.service.user.AuthenticationException;
import bookstoreApp.service.user.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
@RequestMapping(value="/login")
public class LoginController implements WebMvcConfigurer { ;
    AuthenticationService authenticationService;

    @Autowired
    public LoginController( AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }


    @GetMapping()
    public String userForm(Model model) {
        model.addAttribute(new UserDto());
        model.addAttribute("roles", ApplicationConstants.Roles.ROLES);
        return "login";
    }

    public void reintroduceAttributes(Model model, UserDto userDto){
        model.addAttribute(userDto);
        model.addAttribute("roles", ApplicationConstants.Roles.ROLES);
    }

    @PostMapping(params = "registerBtn")
    public String userRegister(@RequestParam String selRole, @ModelAttribute @Valid UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            reintroduceAttributes(model, userDto);
            return "login";
        }
        userDto.role=selRole;
        reintroduceAttributes(model, userDto);
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
            case "employee": return "redirect:/empl/employeeMenu";
            case "administrator": return "redirect:/admin/adminMenu";
            default: return "login";
        }
    }


}
