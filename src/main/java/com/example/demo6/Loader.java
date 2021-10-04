package com.example.demo6;

import org.bson.codecs.pojo.annotations.BsonId;

public class Loader {
    @BsonId
    private String id;
    private String brand;
    private String regnumber;
    private String condition;
    private String site;

    public Loader(String id, String brand, String regnumber, String condition, String site) {
        this.id = id;
        this.brand = brand;
        this.regnumber = regnumber;
        this.condition = condition;
        this.site =site;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getRegnumber() {
        return regnumber;
    }

    public void setRegnumber(String regnumber) {
        this.regnumber = regnumber;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
