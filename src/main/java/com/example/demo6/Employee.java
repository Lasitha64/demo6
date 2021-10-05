package com.example.demo6;

import org.bson.codecs.pojo.annotations.BsonId;

public class Employee {
    @BsonId
    private String employeeName;
    private String employeeAddress;
    private String employeeNIC;
    private String employeeMobile;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeNIC() {
        return employeeNIC;
    }

    public void setEmployeeNIC(String employeeNIC) {
        this.employeeNIC = employeeNIC;
    }

    public String getEmployeeMobile() {
        return employeeMobile;
    }

    public void setEmployeeMobile(String employeeMobile) {
        this.employeeMobile = employeeMobile;
    }

    public Employee(String employeeName, String employeeAddress, String employeeNIC, String employeeMobile) {
        this.employeeName = employeeName;
        this.employeeAddress = employeeAddress;
        this.employeeNIC = employeeNIC;
        this.employeeMobile = employeeMobile;
    }

    public Employee() {
    }
}
