package com.example.demo6;

import org.bson.codecs.pojo.annotations.BsonId;

public class vehicleV {
    @BsonId

    private String brand;
    private String regnumber;
    private String condition;
    private String site;

    public vehicleV( String brand,  String condition, String site,String regnumber) {

        this.brand = brand;
        this.regnumber = regnumber;
        this.condition = condition;
        this.site =site;
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