package edu.famu.myinvestments.controllers;


import edu.famu.myinvestments.models.Investments;
import edu.famu.myinvestments.models.Post;
import edu.famu.myinvestments.models.RestPost;
import edu.famu.myinvestments.models.User;
import edu.famu.myinvestments.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

//GET POST BY USER ID AND CALL FUNCTION
//Doesn't need Excessive LOGIC
//controller connects with the SERVICE---> SERVICE CONNECTS TO THE MODEL
@RestController
@RequestMapping("/users")
public class UserRestController{

    public UserService userService;

    @Autowired
    public UserRestController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(path = "/")
    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        return this.userService.getAllUsers();
    }

    @GetMapping({"/{id}"})
    public User getUserByID(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        return this.userService.getUserByID(id);
    }

    @GetMapping("/{id}/investments")
    public List<Investments> getInvestmentByUserId(@PathVariable(name="id") String id) throws ExecutionException, InterruptedException {
        return userService.getInvestmentByUserId(id);
    }

    @GetMapping("/{id}/posts")
    public List<Post> getPostsByUserId(@PathVariable(name="id") String id) throws ExecutionException, InterruptedException {
        return userService.getPostByUserId(id);
    }

    //HOWWC
    //UPDATE USER BASIC INFO --> ADD FUNCTION IN USER SERVICE
    //UPDATE PROFILE IMAGE... OR COMBINE IT INTO ^
}