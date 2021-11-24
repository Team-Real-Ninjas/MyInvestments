package edu.famu.myinvestments.controllers;


import edu.famu.myinvestments.models.Post;
import edu.famu.myinvestments.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

//GET POST BY USER ID AND CALL FUNCTION
//Doesn't need Excessive LOGIC
//controller connects with the SERVICE---> SERVICE CONNECTS TO THE MODEL
@RestController
@RequestMapping("/api/user")
public class UserRestController{

    public UserService userService;

    @Autowired
    public UserRestController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}/posts")
    public List<Post> getPostsByUserId(@PathVariable(name="id") String id) throws ExecutionException, InterruptedException {
        return userService.getPostByUserId(id);
    }
}