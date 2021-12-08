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
//@RequestMapping("/mysmartinvestments/account")
public class AccountContoller {

    private UserService userService;

    @Autowired
    public AccountContoller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getUsers(Model model) throws ExecutionException, InterruptedException {
        model.addAttribute("user", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/user/{id}")
    public String getUserInformation(@PathVariable("id")String id, Model model) throws ExecutionException, InterruptedException {
        User user = userService.getUserByID(id);
        model.addAttribute("user", user);
        return "account";
    }



}
