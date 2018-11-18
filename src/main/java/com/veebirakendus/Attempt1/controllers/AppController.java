package com.veebirakendus.Attempt1.controllers;

import com.veebirakendus.Attempt1.entity.AdObject;
import com.veebirakendus.Attempt1.entity.User;
//import com.veebirakendus.Attempt1.services.AdService;
import com.veebirakendus.Attempt1.services.AdObjectService;
import com.veebirakendus.Attempt1.services.AdObjectsShowService;
import com.veebirakendus.Attempt1.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Controller
public class AppController {

    //@Autowired
    //private AdService adService;

    @Autowired
    EmailService emailService;

    @Autowired
    AdObjectService adObjectService;

    @Autowired
    AdObjectsShowService adObjectsShowService;


    @GetMapping("/")
    public String index(Model model, Principal user) {
        //model.addAttribute("text", "Testing this place");
        List<AdObject> ads = adObjectsShowService.listAllAds();
        adObjectsShowService.makePicList(ads);
        //List<String> pics = adObjectsShowService.makePicList(ads);
        model.addAttribute("info", adObjectsShowService.listAllAds());
        //model.addAttribute("pictures", pics);
        //model.addAttribute("ads", adObjectsShowService.listAll());
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
    public String meist(Model model) {
        return "meist";
    }

    @GetMapping("/statistics")
    public String stats(Model model) {
        return "statistics";
    }

    @GetMapping("/kuulutused")
    public String kuulutused(Model model) {
        return "minuKuulutused";
    }

    @GetMapping("/kuulutusInfo/{adId}")
    public String kuulutusInfo(Model model, @PathVariable String adId) {
        AdObject ad = adObjectsShowService.getById(Long.parseLong(adId));
        model.addAttribute("ad", ad);
        return "kuulutusInfo";
    }

    @GetMapping("/sendEmail/{adId}")
    public String sendEmail(Model model, @PathVariable String adId) {
        AdObject ad = adObjectsShowService.getById(Long.parseLong(adId));
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            emailService.sendEmail(ad.getEmail(), principal.getName(), ad.getId(), ad.getDescription());
            return "redirect:/";
        } catch (Exception exception) {
            System.out.println("siin"+ exception);
            return "redirect:/";
        }

    }

    @GetMapping("/kuulutus")
    public String kuulutuseLaadimine(Model model) {
        model.addAttribute("adobject", new AdObject());
        return "kuulutus";
    }

    @GetMapping("/minuKuulutused")
    public String myAds(Model model) {
        List<AdObject> ads;
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ads = adObjectsShowService.getAllByGoogleUid(principal.getGoogleUid());
        adObjectsShowService.makePicList(ads); //teeb pildid
        //List<String> pics = adObjectsShowService.makePicList(ads);
        //model.addAttribute("info", adObjectsShowService.listAllAds());
        model.addAttribute("ads", ads);
        //model.addAttribute("pics",pics);
        return "minuKuulutused";
    }

    @PostMapping("/kuulutus")
    public String AdSubmit(@ModelAttribute AdObject adObject, @RequestParam("file") MultipartFile file) {
        //adObject.setId(AdObject.getLastId());
        //AdObject.incrementLastId();
        //System.out.println(adObject.getId());
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal.getGoogleUid());
        adObject.setGoogleUid(principal.getGoogleUid());
        //adObject.setId(Long.parseLong(principal.getGoogleUid()));
        //System.out.println(adObject.getGoogleUid().equals(principal.getGoogleUid()));
        adObjectService.saveAd(adObject, file);
        return "redirect:minuKuulutused";
    }

    @GetMapping("/error")
    public String error(Model model) {
        return "redirect:/";
    }


    @RequestMapping(value = "/minuKuulutused/delete/{adId}", method = RequestMethod.GET)
    public String handleDeleteUser(@PathVariable String adId) {
        System.out.println(adId);
        adObjectsShowService.deleteAd(Long.parseLong(adId));
        System.out.println("test");
        return "redirect:/minuKuulutused";
    }
}
    /*@GetMapping("/kuulutus/{adId}")
    public String kuulutusInfo(@PathVariable String adId){

    }
}*/
