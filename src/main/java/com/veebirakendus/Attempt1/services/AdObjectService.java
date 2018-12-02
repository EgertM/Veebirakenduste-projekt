
package com.veebirakendus.Attempt1.services;

import com.veebirakendus.Attempt1.entity.AdObject;
import com.veebirakendus.Attempt1.repositories.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class AdObjectService {
    private static String imageFolder = "F://temp//";
    @Autowired
    AdRepository adRepository;

    @Transactional
    public void saveAd(AdObject ad) {
        adRepository.save(ad);
    }
}
