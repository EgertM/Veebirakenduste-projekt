package com.veebirakendus.Attempt1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("text", "Testing this place");
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

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("login", "login test");
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @GetMapping("/userSettings")
    public String userSettings(Model model) {
        return "userSettings";
    }

    @GetMapping("/kuulutus")
    public String kuulutus(Model model) {
        return "kuulutus";
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
}
