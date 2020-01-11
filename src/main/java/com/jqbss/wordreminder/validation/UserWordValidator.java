package com.jqbss.wordreminder.validation;

import com.jqbss.wordreminder.model.UserWord;
import com.jqbss.wordreminder.service.UserWordService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserWordValidator implements Validator {

    UserWordService userWordService;

    public UserWordValidator(UserWordService userWordService) {
        this.userWordService = userWordService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserWord.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        UserWord userWord = (UserWord) o;

        if(userWordService.getUserWordByEnglishName(userWord.getEnglishName())!=null){
            errors.rejectValue("englishName","error.englishName.busy");
        }
    }
}
