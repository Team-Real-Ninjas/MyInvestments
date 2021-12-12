package edu.famu.myinvestments.controllers;

import edu.famu.myinvestments.models.User;
import edu.famu.myinvestments.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.ExecutionException;

@Controller
//@RequestMapping("/account")
public class AccountContoller {

    private UserService userService;

    @Autowired
    public AccountContoller(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/account")
    public String getAccountPage(){
        return "account";
    }

    @GetMapping("/account/{user}")
    public String getUsers(@PathVariable("user")String user, Model model) throws ExecutionException, InterruptedException {
        model.addAttribute("user", userService.getAllUsers());
        return "account";
    }

    @GetMapping("/")
    public String getAllUsers(Model model) throws ExecutionException, InterruptedException {
        model.addAttribute("users", userService.getAllUsers());
        return "account";
    }

    @GetMapping("/user/{id}")
    public String getUserInformation(@PathVariable("id")String id, Model model) throws ExecutionException, InterruptedException {
        User user = userService.getUserByID(id);
        model.addAttribute("user", user);
        model.addAttribute("id", user.getId());
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("phoneNumber", user.getPhoneNumber());
        model.addAttribute("email", user.getEmailAddress());
        return "account";
    }

    //Create New post
    @GetMapping("/new/user")
    public String newUser(){
        return "user";
    }

    //Save Data
    @PostMapping(path="/new/save", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String saveUser(User user) throws ExecutionException, InterruptedException {
        userService.createUser(user);
        return"redirect:/";
    }


}
