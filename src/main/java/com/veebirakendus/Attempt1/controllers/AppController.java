package com.veebirakendus.Attempt1.controllers;

import com.veebirakendus.Attempt1.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class AppController {
    @GetMapping("/")
    public String index(Model model, Principal user) {
        model.addAttribute("text", "Testing this place");

        //User userUser = (User)user;
        //model.addAttribute("user", userUser.getUsername());
        return "index";
    }

    @GetMapping("/kontakt")
    public String contact(Model model) {
        model.addAttribute("contact", "testing this");
        return "contact";
    }

    @RequestMapping("/book/{id}")
    public String getBookById(Model model, @PathVariable("id") Long id) {
        model.addAttribute("bookId", id);
        return "book";
    }
    /*@GetMapping("/login")
    public String afterLoggedIn(Model model){
        return "/";
    }*/


    @GetMapping("/userSettings")
    public String userSettings(Model model) {
        return "userSettings";
    }

    @GetMapping("/meist")
    public String meist(Model model){
        return "meist";
    }
    @GetMapping("/kuulutused")
    public String kuulutused(Model model){
        return "minuKuulutused";
    }
    @GetMapping("/kuulutusInfo")
    public String kuulutusInfo(Model model){
        return "kuulutusInfo";
    }
    @GetMapping("/kuulutus")
    public String kuulutuseLaadimine(Model model){
        return "kuulutus";
    }
}
