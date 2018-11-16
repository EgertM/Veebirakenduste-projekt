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
    public List<byte[]> listAll() {
        List<byte[]> ads;
        ads = adRepository.getAllPics();
        return ads;
    }
    @Transactional
    public List<AdObject> listAllAds(){
        List<AdObject> ads;
        ads = adRepository.getAllAds();
        return ads;
    }
    @Transactional
    public List<AdObject> getAllByGoogleUid(String googleUid){
        List<AdObject> ads;
        ads = adRepository.findByGoogleId(googleUid);
        return ads;
    }
    @Transactional
    public List<String> makePicList(List<AdObject> ads){
        List<String> base64List = new ArrayList<>();
        for(AdObject ad : ads){
            String base64 = "data:" + ".jpg" + ";base64, " + Base64Utils.encodeToString(ad.getPic());
            base64List.add(base64);
            System.out.println(base64);
        }
        return base64List;
    }
    @Transactional
    public void deleteAd(Long id){
        adRepository.deleteAd(id);
    }
}
