package bookstoreApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdministratorMenuController {

    @Autowired
    public AdministratorMenuController(){}

    @GetMapping(value ="/administatorMenu")
    public String userForm(Model model) {
        return "administratorMenu";
    }

    @PostMapping(value ="/admistratorMenu", params = "userManageBtn")
    public String userManage() {
        return "manageEmployee";
    }

    @PostMapping(value ="/admistratorMenu", params = "bookManageBtn")
    public String bookManage() {
        return "manageBook";
    }


}
