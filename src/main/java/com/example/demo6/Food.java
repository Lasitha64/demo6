package com.example.demo6;

public class Food {
    private String billNo;
    private String description;
    private String amount;
    private String date;

    public Food(String billNo, String description, String amount, String date) {
        this.billNo = billNo;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public Food() {
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
