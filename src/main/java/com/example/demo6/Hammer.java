package com.example.demo6;


import org.bson.codecs.pojo.annotations.BsonId;

public class Hammer {
    @BsonId
    private String ID;
    private String sparepartname;
    private String Quantity;
    private String Price;
    private String Date;

    public Hammer(String ID, String sparepartname, String quantity, String price, String date) {
        this.ID = ID;
        this.sparepartname = sparepartname;
        Quantity = quantity;
        Price = price;
        Date = date;
    }

    public Hammer() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSparepartname() {
        return sparepartname;
    }

    public void setSparepartname(String sparepartname) {
        this.sparepartname = sparepartname;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
