package edu.famu.myinvestments.controllers;


import edu.famu.myinvestments.services.PostService;
import edu.famu.myinvestments.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mysmartinvestments/home")
public class HomeController {

    private UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

}
