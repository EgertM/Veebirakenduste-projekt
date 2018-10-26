/*
package com.veebirakendus.Attempt1.configuration;

import com.veebirakendus.Attempt1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            System.out.println("USERNAME IS EMPTY");
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            System.out.println("USERNAME ALREADY EXISTS");
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            System.out.println("PASSWORD WONT FIT");
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            System.out.println("DIFFERENT PASSWORDS");
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
    public boolean checkDatabase(Object o){
        User user = (User) o;

        if (userService.findByUsername(user.getUsername()) != null && userService.findByUsername(user.getUsername()).getPassword().equals(((User) o).getPassword())) {
            System.out.println("ALL GOOD");
            return true;

        }
        return false;
    }
}*/
