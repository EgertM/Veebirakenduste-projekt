package com.veebirakendus.Attempt1.services;

import com.veebirakendus.Attempt1.entity.AdObject;
import com.veebirakendus.Attempt1.repositories.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdObjectsShowService {

    @Autowired
    AdRepository adRepository;

    @Transactional
    public List<AdObject> listAllAds(){
        List<AdObject> ads = new ArrayList<>();
        adRepository.findAll().forEach(ads::add);
        return ads;
    }
    @Transactional
    public List<AdObject> getAllByGoogleUid(String googleUid){
        List<AdObject> ads;
        ads = adRepository.findByGoogleId(googleUid);
        return ads;
    }
    /*@Transactional
    public void makePicList(List<AdObject> ads){
        //List<String> base64List = new ArrayList<>();
        for(AdObject ad : ads){
            String base64 = "data:" + ".jpg" + ";base64, " + Base64Utils.encodeToString(ad.getPic());
            //base64List.add(base64);
            //ad.setPictureString(base64);
            adRepository.save(ad);
            //System.out.println(base64);
        }
    }*/
    @Transactional
    public AdObject getById(Long id){
        return adRepository.findById(id);
    }

    public void deleteAd(Long id){
        adRepository.deleteAd(id);
    }
}
