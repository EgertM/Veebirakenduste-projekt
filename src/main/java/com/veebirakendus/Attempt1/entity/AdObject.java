package com.veebirakendus.Attempt1.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AdObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String pictureSource;
    private String googleUid;
    private String hind;


    public long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureSource() {
        return pictureSource;
    }

    public void setPictureSource(String pictureSource) {
        this.pictureSource = pictureSource;
    }

    public void setGoogleUid(String googleUid) {
        this.googleUid = googleUid;
    }

    public String getGoogleUid() {
        return googleUid;
    }

    public String getHind() {
        return hind;
    }

    public void setHind(String hind) {
        this.hind = hind;
    }
}
