package com.veebirakendus.Attempt1.controllers;

import com.veebirakendus.Attempt1.entity.AdObject;
import com.veebirakendus.Attempt1.entity.User;
import com.veebirakendus.Attempt1.repositories.AdRepository;
import com.veebirakendus.Attempt1.services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.security.Principal;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private AdService adService;

    @Autowired
    private AdRepository adRepository;

    @GetMapping("/")
    public String index(Model model, Principal user) {
        model.addAttribute("text", "Testing this place");
        //List<AdObject> ads = (List<AdObject>) adRepository.findAll();
        //model.addAttribute("ads", ads);
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
        model.addAttribute("adobject", new AdObject());
        return "kuulutus";
    }
    @GetMapping("/statistika")
    public String statistics(Model model){
        return "statistics";
    }
    @PostMapping("/kuulutus")
    public String AdSubmit(@ModelAttribute AdObject adObject){
        //adObject.setId(AdObject.getLastId());
        //AdObject.incrementLastId();
        System.out.println(adObject.getId());
        User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal.getGoogleUid());
        adObject.setGoogleUid(principal.getGoogleUid());
        //adObject.setId(Long.parseLong(principal.getGoogleUid()));
        //System.out.println(adObject.getGoogleUid().equals(principal.getGoogleUid()));
        adRepository.save(adObject);
        return "minuKuulutused";
    }
}
