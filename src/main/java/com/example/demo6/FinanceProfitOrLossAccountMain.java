//Page y

package com.example.demo6;

public class FinanceProfitOrLossAccountMain {
    private String PLid;
    private String PLdate;
    private String PLsales;
    private String PLcos;
    private String PLoi;
    private String PLox;

    private String PLgross;
    private String PLnet;

    public FinanceProfitOrLossAccountMain(String pLid, String pLdate, String pLsales, String pLcos, String pLoi, String pLox, String pLgross, String pLnet) {
        PLid = pLid;
        PLdate = pLdate;
        PLsales = pLsales;
        PLcos = pLcos;
        PLoi = pLoi;
        PLox = pLox;
        PLgross = pLgross;
        PLnet = pLnet;
    }

    public String getPLid() {
        return PLid;
    }

    public void setPLid(String PLid) {
        this.PLid = PLid;
    }

    public String getPLdate() {
        return PLdate;
    }

    public void setPLdate(String PLdate) {
        this.PLdate = PLdate;
    }

    public String getPLsales() {
        return PLsales;
    }

    public void setPLsales(String PLsales) {
        this.PLsales = PLsales;
    }

    public String getPLcos() {
        return PLcos;
    }

    public void setPLcos(String PLcos) {
        this.PLcos = PLcos;
    }

    public String getPLoi() {
        return PLoi;
    }

    public void setPLoi(String PLoi) {
        this.PLoi = PLoi;
    }

    public String getPLox() {
        return PLox;
    }

    public void setPLox(String PLox) {
        this.PLox = PLox;
    }

    public String getPLgross() {
        return PLgross;
    }

    public void setPLgross(String PLgross) {
        this.PLgross = PLgross;
    }

    public String getPLnet() {
        return PLnet;
    }

    public void setPLnet(String PLnet) {
        this.PLnet = PLnet;
    }
}
