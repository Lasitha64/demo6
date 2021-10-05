package com.example.demo6;

public class FinancePurchaseJournalMain {
    private String PJ_ID;
    private String PJDate;
    private String PJlp;
    private String PJdis;
    private String PJsupplier;
    private String PJtotal_value;

    public FinancePurchaseJournalMain(String pJ_ID, String pj_id, String pjDate, String pJlp, String pJdis, String pJsupplier, String pJtotal_value) {
        PJ_ID = pj_id;
        PJDate = pjDate;
        PJlp = pJlp;
        PJdis = pJdis;
        PJsupplier = pJsupplier;
        PJtotal_value = pJtotal_value;
    }

    public String getPJ_ID() {
        return PJ_ID;
    }

    public void setPJ_ID(String PJ_ID) {
        this.PJ_ID = PJ_ID;
    }

    public String getPJDate() {
        return PJDate;
    }

    public void setPJDate(String PJDate) {
        this.PJDate = PJDate;
    }

    public String getPJlp() {
        return PJlp;
    }

    public void setPJlp(String PJlp) {
        this.PJlp = PJlp;
    }

    public String getPJdis() {
        return PJdis;
    }

    public void setPJdis(String PJdis) {
        this.PJdis = PJdis;
    }

    public String getPJsupplier() {
        return PJsupplier;
    }

    public void setPJsupplier(String PJsupplier) {
        this.PJsupplier = PJsupplier;
    }

    public String getPJtotal_value() {
        return PJtotal_value;
    }

    public void setPJtotal_value(String PJtotal_value) {
        this.PJtotal_value = PJtotal_value;
    }
}
