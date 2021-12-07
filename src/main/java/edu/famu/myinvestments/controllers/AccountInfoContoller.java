package edu.famu.myinvestments.controllers;

import edu.famu.myinvestments.models.User;
import edu.famu.myinvestments.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/mysmartinvestments/account")
public class AccountInfoContoller {

    private UserService userService;

    @Autowired
    public AccountInfoContoller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public String getUserInformation(@PathVariable("id")String id, Model model) throws ExecutionException, InterruptedException {
        User user = userService.getUserByID(id);
        model.addAttribute("user", user);
        return "account";
    }



}
