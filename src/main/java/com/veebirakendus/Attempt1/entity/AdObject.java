package com.veebirakendus.Attempt1.entity;

import javax.persistence.*;
import org.springframework.core.io.Resource;

@Entity
@Table(name = "ad_objects")
public class AdObject {

    //tatic Long lastId = 0L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String phone;
    private String description;
    //private Resource imageFile;
    private String googleUid;
    private String hind;
    private String picName;


    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return email;
    }

    public void setName(String name) {
        this.email = name;
    }

    /*public static Long getLastId() {
        return lastId;
    }

    public static void incrementLastId() {
        lastId++;
    }*/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        this.hind = " " + hind + " €";
    }

    /*public AdObject(){}

    public AdObject(byte[] pic, String description, String hind, String googleUid){
        this.pic = pic;
        this.googleUid = googleUid;
        this.description = description;
        this.hind = hind;
    }*/



    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

}
