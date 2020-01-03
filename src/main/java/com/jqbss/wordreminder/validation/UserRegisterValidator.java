package com.jqbss.wordreminder.validation;

import com.jqbss.wordreminder.model.User;

import com.jqbss.wordreminder.reposiotory.UserRepository;
import com.jqbss.wordreminder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserRegisterValidator implements Validator {

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        User user = (User)o;

        ValidationUtils.rejectIfEmpty(errors,"userLogin", "error.userLogin.empty");
        ValidationUtils.rejectIfEmpty(errors, "userEmail", "error.userEmail.empty");
        ValidationUtils.rejectIfEmpty(errors,"password","error.password.empty");
        ValidationUtils.rejectIfEmpty(errors, "passwordConfirm","error.password.empty");

        if(!user.getPassword().equals(user.getPasswordConfirm())){
            errors.rejectValue("passwordConfirm","error.password.confirm");
        }

        if(userService.findByUserLogin(user.getUserLogin())!=null){
            errors.rejectValue("userLogin","error.userLogin.taken");
        }

        if(userService.findByUserEmail(user.getUserEmail())!=null){
            errors.rejectValue("userEmail","error.userEmail.taken");
        }

        if (user.getPassword().length()<6 || user.getPassword().length()>25){
            errors.rejectValue("password","error.password.length");
        }
    }
}
