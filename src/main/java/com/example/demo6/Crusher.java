package com.example.demo6;

public class Crusher {

    private String id;
    private String name;
    private int quantity;
    private double price;
    private String date;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Crusher(String id, String name, int quantity, double price, String date) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }
}
