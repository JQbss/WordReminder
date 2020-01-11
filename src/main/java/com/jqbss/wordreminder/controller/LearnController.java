package com.jqbss.wordreminder.controller;

import com.jqbss.wordreminder.model.UserWord;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class LearnController {

    @GetMapping("/learn")
    public String Learn(){
        return "learn";
    }

    @GetMapping("/addword")
    public String AddWord(UserWord userWord){
        return "addword";
    }

    @PostMapping("/addword")
    public String AddWord(@Valid UserWord userWord, BindingResult bindingResult){
        return "addword";
    }
}
