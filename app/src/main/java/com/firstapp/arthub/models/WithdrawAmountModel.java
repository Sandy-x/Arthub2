package com.firstapp.arthub.models;

public class WithdrawAmountModel {
   String AccountNo,Ifsccode,Accounttype,Accountname,Requestamount,Availableamount,Issueddate,Status;

    public WithdrawAmountModel() {
    }


    public WithdrawAmountModel(String accountNo, String ifsccode, String accounttype, String accountname, String requestamount, String availableamount, String issueddate, String status) {
        AccountNo = accountNo;
        Ifsccode = ifsccode;
        Accounttype = accounttype;
        Accountname = accountname;
        Requestamount = requestamount;
        Availableamount = availableamount;
        Issueddate = issueddate;
        Status = status;
    }


    public String getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo(String accountNo) {
        AccountNo = accountNo;
    }

    public String getIfsccode() {
        return Ifsccode;
    }

    public void setIfsccode(String ifsccode) {
        Ifsccode = ifsccode;
    }

    public String getAccounttype() {
        return Accounttype;
    }

    public void setAccounttype(String accounttype) {
        Accounttype = accounttype;
    }

    public String getAccountname() {
        return Accountname;
    }

    public void setAccountname(String accountname) {
        Accountname = accountname;
    }

    public String getRequestamount() {
        return Requestamount;
    }

    public void setRequestamount(String requestamount) {
        Requestamount = requestamount;
    }

    public String getAvailableamount() {
        return Availableamount;
    }

    public void setAvailableamount(String availableamount) {
        Availableamount = availableamount;
    }

    public String getIssueddate() {
        return Issueddate;
    }

    public void setIssueddate(String issueddate) {
        Issueddate = issueddate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
