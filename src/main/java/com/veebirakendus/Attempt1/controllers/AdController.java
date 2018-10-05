package com.veebirakendus.Attempt1.controllers;

import com.veebirakendus.Attempt1.entity.AdObject;
import com.veebirakendus.Attempt1.repositories.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AdController {
    @Autowired
    private AdRepository adRepository;

    @RequestMapping(value = "/kuulutus",method = RequestMethod.POST)
    public ResponseEntity<Void> addAd(){
        AdObject adObject = new AdObject();
        adObject.setName("Test1");
        adObject.setDescription("test1_des");
        adRepository.save(adObject);
        return ResponeEntity.ok().build();
    }
    @RequestMapping(value = "/kuulutus", method = RequestMethod.GET)
    public List<AdObject> getAds(){
        return adRepository.findAll();
    }
    @RequestMapping("/kuulutus")
    public String kuulutus(Model model) {
        model.addAttribute("adObject", adRepository.findById());
        return "kuulutus";
    }
}
