package com.example.demo6;

public class FinanceCashbookMain {
    private String CID;
    private String CDateCr;
    private String CDiscriptionCr;
    private String CLFcr;
    private String CValueCr;
    private String CDateDr;
    private String CDiscriptionDr;
    private String CLFdr;
    private String CValueDr;

    public FinanceCashbookMain(String cid, String cDateCr, String cDiscriptionCr, String clFcr, String cValueCr, String cDateDr, String cDiscriptionDr, String clFdr, String cValueDr) {
        CID = cid;
        CDateCr = cDateCr;
        CDiscriptionCr = cDiscriptionCr;
        CLFcr = clFcr;
        CValueCr = cValueCr;
        CDateDr = cDateDr;
        CDiscriptionDr = cDiscriptionDr;
        CLFdr = clFdr;
        CValueDr = cValueDr;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getCDateCr() {
        return CDateCr;
    }

    public void setCDateCr(String CDateCr) {
        this.CDateCr = CDateCr;
    }

    public String getCDiscriptionCr() {
        return CDiscriptionCr;
    }

    public void setCDiscriptionCr(String CDiscriptionCr) {
        this.CDiscriptionCr = CDiscriptionCr;
    }

    public String getCLFcr() {
        return CLFcr;
    }

    public void setCLFcr(String CLFcr) {
        this.CLFcr = CLFcr;
    }

    public String getCValueCr() {
        return CValueCr;
    }

    public void setCValueCr(String CValueCr) {
        this.CValueCr = CValueCr;
    }

    public String getCDateDr() {
        return CDateDr;
    }

    public void setCDateDr(String CDateDr) {
        this.CDateDr = CDateDr;
    }

    public String getCDiscriptionDr() {
        return CDiscriptionDr;
    }

    public void setCDiscriptionDr(String CDiscriptionDr) {
        this.CDiscriptionDr = CDiscriptionDr;
    }

    public String getCLFdr() {
        return CLFdr;
    }

    public void setCLFdr(String CLFdr) {
        this.CLFdr = CLFdr;
    }

    public String getCValueDr() {
        return CValueDr;
    }

    public void setCValueDr(String CValueDr) {
        this.CValueDr = CValueDr;
    }
}
