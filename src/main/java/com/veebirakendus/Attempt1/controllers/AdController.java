/*
package com.veebirakendus.Attempt1.controllers;

import com.veebirakendus.Attempt1.entity.AdObject;
import com.veebirakendus.Attempt1.repositories.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class AdController {
    @Autowired
    private AdRepository adRepository;

    @RequestMapping(value = "/kuulutus",method = RequestMethod.POST)
    public ResponseEntity<Void> addAd(@RequestBody AdObject adObject){
        adRepository.save(adObject);
        //AdObject adObject = new AdObject();
        //adObject.setName("Test1");
        //adObject.setDescription("test1_des");
        //adRepository.save(adObject);
        return ResponseEntity.ok().build();
    }
    @RequestMapping(value = "/kuulutus", method = RequestMethod.GET)
    public ResponseEntity<Iterable<AdObject>> getAds(){
        return ResponseEntity.ok(adRepository.findAll());
    }
    @RequestMapping(value = "/kuulutus/{id}", method = RequestMethod.GET)
    public String getKuulutusById(Model model, @PathVariable("id") Long id) {
        Optional<AdObject> adObject = adRepository.findById(id);
        if(adObject.isPresent()){
            System.out.println(adObject.get());
            model.addAttribute("kuulutus", adObject.get());
        }
        else{
            model.addAttribute("kuulutus", new AdObject());
        }

        return "kuulutus";
    }
}
*/
