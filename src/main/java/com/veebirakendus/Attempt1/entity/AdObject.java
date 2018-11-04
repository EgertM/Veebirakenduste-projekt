package com.veebirakendus.Attempt1.entity;

import javax.persistence.*;

@Entity
public class AdObject {

    static Long lastId = 0L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

    @Lob
    @Column(name="pic")
    private byte[] pic;
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

    public static Long getLastId() {
        return lastId;
    }

    public static void incrementLastId() {
        lastId++;
    }

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
        this.hind = hind;
    }

    /*public AdObject(){}

    public AdObject(byte[] pic, String description, String hind, String googleUid){
        this.pic = pic;
        this.googleUid = googleUid;
        this.description = description;
        this.hind = hind;
    }*/


    public byte[] getPic(){
        return this.pic;
    }

    public void setPic(byte[] pic){
        this.pic = pic;
    }
}
