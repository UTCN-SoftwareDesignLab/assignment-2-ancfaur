package bookstoreApp.controller;

import bookstoreApp.service.user.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController { ;
    AuthenticationService authenticationService;

    @Autowired
    public LoginController( AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @Order(value = 1)
    public String index() {
        return "login";
    }




}
