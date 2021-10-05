//page w

package com.example.demo6;

public class FinanceSalesJournalMain {
    private String SJ_ID;
    private String SJDate;
    private String SJlp;
    private String SJdis;
    private String SJcustomer;
    private String SJtotal_value;

    public FinanceSalesJournalMain(String sJ_ID, String sj_id, String sjDate, String sJlp, String sJdis, String sJcustomer, String sJtotal_value) {
        SJ_ID = sj_id;
        SJDate = sjDate;
        SJlp = sJlp;
        SJdis = sJdis;
        SJcustomer = sJcustomer;
        SJtotal_value = sJtotal_value;
    }

    public String getSJ_ID() {
        return SJ_ID;
    }

    public void setSJ_ID(String SJ_ID) {
        this.SJ_ID = SJ_ID;
    }

    public String getSJDate() {
        return SJDate;
    }

    public void setSJDate(String SJDate) {
        this.SJDate = SJDate;
    }

    public String getSJlp() {
        return SJlp;
    }

    public void setSJlp(String SJlp) {
        this.SJlp = SJlp;
    }

    public String getSJdis() {
        return SJdis;
    }

    public void setSJdis(String SJdis) {
        this.SJdis = SJdis;
    }

    public String getSJcustomer() {
        return SJcustomer;
    }

    public void setSJcustomer(String SJcustomer) {
        this.SJcustomer = SJcustomer;
    }

    public String getSJtotal_value() {
        return SJtotal_value;
    }

    public void setSJtotal_value(String SJtotal_value) {
        this.SJtotal_value = SJtotal_value;
    }
}
