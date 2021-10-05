package com.example.demo6;

import org.bson.codecs.pojo.annotations.BsonId;

public class FinanceGeneralJournalMain {
    @BsonId
    private String GJ_ID;
    private String GJDate;
    private String Description;
    private String VoucherNo;

    private String Debit;
    private String Credit;


    public FinanceGeneralJournalMain(String gj_id, String gjDate, String description, String voucherNo, String debit, String credit) {
        GJ_ID = gj_id;
        GJDate = gjDate;
        Description = description;
        VoucherNo = voucherNo;
        Debit = debit;
        Credit = credit;
    }


    public String getGJ_ID() {
        return GJ_ID;
    }

    public void setGJ_ID(String GJ_ID) {
        this.GJ_ID = GJ_ID;
    }

    public String getGJDate() {
        return GJDate;
    }

    public void setGJDate(String GJDate) {
        this.GJDate = GJDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getVoucherNo() {
        return VoucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        VoucherNo = voucherNo;
    }

    public String getDebit() {
        return Debit;
    }

    public void setDebit(String debit) {
        Debit = debit;
    }

    public String getCredit() {
        return Credit;
    }

    public void setCredit(String credit) {
        Credit = credit;
    }
}
