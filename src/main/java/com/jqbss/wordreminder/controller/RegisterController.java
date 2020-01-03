package com.jqbss.wordreminder.controller;

import com.jqbss.wordreminder.model.User;
import com.jqbss.wordreminder.service.UserService;
import com.jqbss.wordreminder.service.implementation.UserServiceImpl;
import com.jqbss.wordreminder.validation.UserRegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class RegisterController {

    UserService userService;
    UserRegisterValidator userRegisterValidator;

    @Autowired
    public RegisterController(UserServiceImpl userService, UserRegisterValidator userRegisterValidator) {
        this.userService = userService;
        this.userRegisterValidator = userRegisterValidator;
    }

    @GetMapping(value = "/register")
    public String Register(User user){
        return "register";
    }

    @PostMapping("/register")
    public String Register(@Valid User user, BindingResult bindingResult){
        userRegisterValidator.validate(user,bindingResult);
        if(bindingResult.hasErrors()){
            return "register";
        }
        userService.addUser(user);
        return "redirect:/login";
    }
}
