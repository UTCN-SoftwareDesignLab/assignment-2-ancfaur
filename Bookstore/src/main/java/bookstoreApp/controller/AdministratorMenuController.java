package bookstoreApp.controller;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value ="/adminMenu")
public class AdministratorMenuController {

    public AdministratorMenuController(){}

    @GetMapping()
    @Order(value = 1)
    public String index() {
        return "administratorMenu";
    }

    @PostMapping(params = "userManageBtn")
    public String userManage() {
        return "redirect:/employeeManagement";
    }

    @PostMapping(params = "bookManageBtn")
    public String bookManage() {
        return "redirect:/authorBookManagement";
    }


}
