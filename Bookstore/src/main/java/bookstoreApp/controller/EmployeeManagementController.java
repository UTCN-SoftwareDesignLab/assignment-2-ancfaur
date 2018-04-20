package bookstoreApp.controller;

import bookstoreApp.dto.UserDto;
import bookstoreApp.service.user.AuthenticationService;
import bookstoreApp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeManagementController {
private AuthenticationService authenticationService;
private UserService userService;

@Autowired
public EmployeeManagementController(AuthenticationService authenticationService, UserService userService){
    this.authenticationService = authenticationService;
    this.userService = userService;
}


    @PostMapping(value ="/createUser", params = "createBtn")
    public String create(@ModelAttribute UserDto userDto) {
        authenticationService.register(userDto);
        return "manageEmployee";
    }


}
