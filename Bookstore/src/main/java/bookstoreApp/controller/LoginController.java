package bookstoreApp.controller;

import bookstoreApp.entity.Book;
import bookstoreApp.entity.Role;
import bookstoreApp.service.role.RoleService;
import bookstoreApp.service.user.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginController {
    AuthenticationService authenticationService;
    RoleService roleService;

    @Autowired
    public LoginController(RoleService roleService, AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String findAll(Model model) {
        return "login";
    }
}
