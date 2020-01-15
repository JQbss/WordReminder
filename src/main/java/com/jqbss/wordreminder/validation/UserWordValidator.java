package com.jqbss.wordreminder.validation;

import com.jqbss.wordreminder.model.User;
import com.jqbss.wordreminder.model.UserWord;
import com.jqbss.wordreminder.service.UserService;
import com.jqbss.wordreminder.service.UserWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserWordValidator implements Validator {

    UserWordService userWordService;
    UserService userService;

    public UserWordValidator(UserWordService userWordService, UserService userService) {
        this.userWordService = userWordService;
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserWord.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        UserWord userWord = (UserWord) o;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserLogin(auth.getName());
        if(userWordService.getUserWordByEnglishNameAndUser(userWord.getEnglishName(),user)!=null){
            errors.rejectValue("englishName","error.englishName.busy");
        }
    }
}
