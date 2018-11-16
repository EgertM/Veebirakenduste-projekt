package com.veebirakendus.Attempt1.controllers;

import com.veebirakendus.Attempt1.entity.AdObject;
import com.veebirakendus.Attempt1.entity.User;
import com.veebirakendus.Attempt1.repositories.AdRepository;
//import com.veebirakendus.Attempt1.services.AdService;
import com.veebirakendus.Attempt1.services.AdObjectService;
import com.veebirakendus.Attempt1.services.AdObjectsShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {

    //@Autowired
    //private AdService adService;



    @Autowired
    AdObjectService adObjectService;

    @Autowired
    AdObjectsShowService adObjectsShowService;

    @GetMapping("/")
    public String index(Model model, Principal user) {
        model.addAttribute("text", "Testing this place");
        List<String> base64List = new ArrayList<>();
        for(byte[] array : adObjectsShowService.listAll()){
            String base64 = "data:" + ".jpg" + ";base64, " + Base64Utils.encodeToString(array);
            base64List.add(base64);
        }
        model.addAttribute("info", adObjectsShowService.listAllAds());
        model.addAttribute("pictures", base64List);
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

    @GetMapping("/minuKuulutused")
    public String myAds(Model model){
        List<AdObject> ads;
        User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ads = adObjectsShowService.getAllByGoogleUid(principal.getGoogleUid());
        List<String> pics = adObjectsShowService.makePicList(ads);
        //model.addAttribute("info", adObjectsShowService.listAllAds());
        model.addAttribute("ads", ads);
        model.addAttribute("pics",pics);
        return "minuKuulutused";
    }
    @PostMapping("/kuulutus")
    public String AdSubmit(@ModelAttribute AdObject adObject, @RequestParam("file") MultipartFile file){
        adObject.setId(AdObject.getLastId());
        AdObject.incrementLastId();
        System.out.println(adObject.getId());
        User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal.getGoogleUid());
        adObject.setGoogleUid(principal.getGoogleUid());
        //adObject.setId(Long.parseLong(principal.getGoogleUid()));
        //System.out.println(adObject.getGoogleUid().equals(principal.getGoogleUid()));
        adObjectService.saveAd(adObject,file);
        return "redirect:minuKuulutused";
    }
    @GetMapping("/error")
    public String error(Model model){
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
