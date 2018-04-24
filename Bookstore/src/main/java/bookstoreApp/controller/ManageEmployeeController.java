package bookstoreApp.controller;

import bookstoreApp.constants.ApplicationConstants;
import bookstoreApp.dto.UserDto;
import bookstoreApp.service.user.AuthenticationService;
import bookstoreApp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value="/admin/manageEmployee")
public class ManageEmployeeController implements WebMvcConfigurer {
    AuthenticationService authenticationService;
    UserService userService;

    @Autowired
    public ManageEmployeeController(AuthenticationService authenticationService, UserService userService){
        this.authenticationService = authenticationService;
        this.userService = userService;
}


    @GetMapping()
    public String userForm(Model model) {
        model.addAttribute(new UserDto());
        model.addAttribute("roles", ApplicationConstants.Roles.ROLES);
        return "manageEmployee";
    }

    @PostMapping(params = "createBtn")
    public String createUser(@RequestParam String selRole, @ModelAttribute @Valid UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", ApplicationConstants.Roles.ROLES);
            return "manageEmployee";
        }
        userDto.role= selRole;
        authenticationService.register(userDto);
        return "redirect:/admin/manageEmployee";
    }

    @PostMapping(params = "updateBtn")
    public String updateUser(@RequestParam String selRole, @ModelAttribute @Valid UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", ApplicationConstants.Roles.ROLES);
            return "manageEmployee";
        }
        userDto.role= selRole;
        userService.update(userDto);
        return "redirect:/admin/manageEmployee";
    }

    @PostMapping(params = "deleteBtn")
    public String deleteUser(@ModelAttribute UserDto userDto) {
        userService.delete(userDto.id);
        return "redirect:/admin/manageEmployee";
    }

    @PostMapping(value = "/showAll", params = "showAllBtn")
    public String showAll(Model model) {
        List<UserDto> userDtos = userService.findAll();
        model.addAttribute("userDtos", userDtos);
        return "userTable";
    }

    @PostMapping(value = "/logout", params="logoutBtn")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

}
