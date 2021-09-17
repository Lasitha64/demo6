package com.example.demo6;

import org.bson.codecs.pojo.annotations.BsonId;

public class Crusher {
@BsonId
    private String id;
    private String name;
    private String quantity;
    private String price;
    private String date;

    public Crusher(String id, String name, String quantity, String price, String date) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String  getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
