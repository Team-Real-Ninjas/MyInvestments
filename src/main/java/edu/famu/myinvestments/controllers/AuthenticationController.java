package edu.famu.myinvestments.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String getLogin()
    {
        return "login";
    }


    /**
     * Used to create the session. Does not return a template like most controllers
     */
    @GetMapping("/session")
    public ResponseEntity createSession()
    {

        return new ResponseEntity(HttpStatus.OK);
    }
}
