package bookstoreApp.controller;

import bookstoreApp.dto.UserDto;
import bookstoreApp.service.user.AuthenticationService;
import bookstoreApp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value="/employeeManagement")
public class EmployeeManagementController {
    AuthenticationService authenticationService;
    UserService userService;

    @Autowired
    public EmployeeManagementController( AuthenticationService authenticationService, UserService userService){
        this.authenticationService = authenticationService;
        this.userService = userService;
}

    @GetMapping()
    public String userForm(Model model) {
        model.addAttribute(new UserDto());
        return "manageEmployee";
    }

    @PostMapping(params = "createBtn")
    public String createUser(@ModelAttribute UserDto userDto) {
        authenticationService.register(userDto);
        return "manageEmployee";
    }

    @PostMapping(params = "updateBtn")
    public String updateUser(@ModelAttribute UserDto userDto) {
        userService.update(userDto);
        return "manageEmployee";
    }

    @PostMapping(params = "deleteBtn")
    public String deleteUser(@ModelAttribute UserDto userDto) {
        userService.delete(userDto.id);
        return "manageEmployee";
    }

    @PostMapping(value = "/showAll", params = "showAllBtn")
    public String showAll(Model model) {
        List<UserDto> userDtos = userService.findAll();
        model.addAttribute("userDtos", userDtos);
        return "userTable";
    }

}
