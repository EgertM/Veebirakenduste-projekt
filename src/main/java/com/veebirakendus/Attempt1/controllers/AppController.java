package com.veebirakendus.Attempt1.controllers;

import com.veebirakendus.Attempt1.entity.AdObject;
import com.veebirakendus.Attempt1.entity.User;
//import com.veebirakendus.Attempt1.services.AdService;
import com.veebirakendus.Attempt1.services.AdObjectService;
import com.veebirakendus.Attempt1.services.AdObjectsShowService;
import com.veebirakendus.Attempt1.services.EmailService;
import com.veebirakendus.Attempt1.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AppController{

    private final StorageService storageService;

    @Autowired
    public AppController(StorageService storageService) {
        this.storageService = storageService;
    }
    //@Autowired
    //private AdService adService;

    @Autowired
    EmailService emailService;

    @Autowired
    AdObjectService adObjectService;

    @Autowired
    AdObjectsShowService adObjectsShowService;

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
        //ad.setImage(storageService.loadAsResource(ad.getPicName()));
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
        //adObjectsShowService.makePicList(ads); //teeb pildid
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
        adObject.setPicName(file.getOriginalFilename());
        //adObject.setId(Long.parseLong(principal.getGoogleUid()));
        //System.out.println(adObject.getGoogleUid().equals(principal.getGoogleUid()));
        storageService.store(file);
        System.out.println(storageService.loadAll());
        System.out.println(storageService.load(file.getOriginalFilename()));
        //adObject.setImage(storageService.loadAsResource(file.getOriginalFilename()));
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

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {
        List<AdObject> ads = adObjectsShowService.listAllAds();
        //adObjectsShowService.makePicList(ads);
        //List<String> pics = adObjectsShowService.makePicList(ads);
        model.addAttribute("info", adObjectsShowService.listAllAds());

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(AppController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "index";
    }
}
    /*@GetMapping("/kuulutus/{adId}")
    public String kuulutusInfo(@PathVariable String adId){

    }
}*/
