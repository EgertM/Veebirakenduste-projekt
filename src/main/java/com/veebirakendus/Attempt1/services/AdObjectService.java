
package com.veebirakendus.Attempt1.services;

import com.veebirakendus.Attempt1.entity.AdObject;
import com.veebirakendus.Attempt1.repositories.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Objects;

@Service
public class AdObjectService {

    @Autowired
    AdRepository adRepository;

    @Transactional
    public void saveAd(AdObject ad, MultipartFile file) {


        try {


            byte[] byteObjects = new byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }


            ad.setPic(byteObjects);

            adRepository.save(ad);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
