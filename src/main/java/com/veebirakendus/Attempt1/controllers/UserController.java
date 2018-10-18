package com.veebirakendus.Attempt1.controllers;

import com.veebirakendus.Attempt1.configuration.SecurityService;
import com.veebirakendus.Attempt1.configuration.UserService;
import com.veebirakendus.Attempt1.configuration.UserValidator;
import com.veebirakendus.Attempt1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            System.out.println("ERRORS IN BINDING RESULT");
            for (Object object: bindingResult.getAllErrors()) {
                if (object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;
                    System.out.println("FIELDERROR");
                    System.out.println(fieldError.getField());
                }
                if (object instanceof ObjectError) {
                    ObjectError objectError = (ObjectError) object;
                    System.out.println("OBJECTERROR");
                    System.out.println(objectError.getObjectName());
                }
            }

            return "register";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
        System.out.println("REGISTERED SUCCESSFULLY");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        return "login";
    }
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@ModelAttribute("loginForm") User loginForm, Model model){
        if(userValidator.checkDatabase(loginForm)){
            User u = userService.findByUsername(loginForm.getUsername());
            model.addAttribute("loginForm", u);
            return "index";
        }
        return "login";
    }

}