package com.example.demo6;

import org.bson.codecs.pojo.annotations.BsonId;

public class serviceS {
    @BsonId

    private String id;
    private String description;
    private String date;
    private String price;

    public serviceS(String id, String description, String date, String price) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}