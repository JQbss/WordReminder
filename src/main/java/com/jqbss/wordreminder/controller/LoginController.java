package com.jqbss.wordreminder.controller;

import com.jqbss.wordreminder.model.User;
import com.jqbss.wordreminder.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LoginController {

    UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String Login(){
        return "login";
    }

    @GetMapping("/test")
    @ResponseBody
    public List<User> Test(){
        return userService.getAllUsers();
    }
}
