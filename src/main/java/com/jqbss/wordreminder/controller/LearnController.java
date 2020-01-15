package com.jqbss.wordreminder.controller;

import com.jqbss.wordreminder.model.UserWord;
import com.jqbss.wordreminder.model.User;
import com.jqbss.wordreminder.reposiotory.UserRepository;
import com.jqbss.wordreminder.service.UserService;
import com.jqbss.wordreminder.service.UserWordService;
import com.jqbss.wordreminder.validation.UserWordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class LearnController {

    UserWordService userWordService;
    UserWordValidator userWordValidator;
    UserService userService;

    public LearnController(UserWordService userWordService, UserWordValidator userWordValidator, UserService userService) {
        this.userWordService = userWordService;
        this.userWordValidator = userWordValidator;
        this.userService = userService;
    }

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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserLogin(auth.getName());
        userWord.setUser(user);
        userWordValidator.validate(userWord,bindingResult);
        if(bindingResult.hasErrors()){
            return "addword";
        }
        userWordService.addUserWord(userWord);
        return "redirect:/learn";
    }

    @GetMapping("/allwords")
    public String AllWords(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserLogin(auth.getName());
        model.addAttribute("words", userWordService.getAllUserWordsByUser(user));
        return "allwords";
    }
}
