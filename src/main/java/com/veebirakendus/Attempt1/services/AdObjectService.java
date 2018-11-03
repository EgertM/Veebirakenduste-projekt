/*
package com.veebirakendus.Attempt1.services;

import com.veebirakendus.Attempt1.entity.AdObject;
import com.veebirakendus.Attempt1.repositories.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class AdObjectService {

    @Autowired
    private AdRepository AdFileRepository;

    public AdObject storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());


            AdObject AdObject = new AdObject(fileName, file.getContentType(), file.getBytes());

            return AdFileRepository.save(dbFile);
    }

    public DBFile getFile(String fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
}*/
