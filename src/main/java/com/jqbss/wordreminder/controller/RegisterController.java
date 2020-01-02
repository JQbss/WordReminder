package com.jqbss.wordreminder.controller;

import com.jqbss.wordreminder.model.User;
import com.jqbss.wordreminder.reposiotory.UserRepository;
import com.jqbss.wordreminder.service.UserService;
import com.jqbss.wordreminder.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RegisterController {

    UserService userService;

    @Autowired
    public RegisterController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/register")
    public String Register(){
        return "register";
    }

    @PostMapping("/register")
    public String Register(@ModelAttribute("user") @Valid User user){
        User checkUser = userService.findUserByEmail(user.getUserEmail());
        userService.addUser(user);
        return "login";
    }
}
