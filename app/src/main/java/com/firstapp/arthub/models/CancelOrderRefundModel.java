package com.firstapp.arthub.models;

public class CancelOrderRefundModel {
    String UserId,AccountName,AccountNo,ifsccode,Accounttype,totalcharges,cancelDate;

    public CancelOrderRefundModel(String userId, String accountName, String accountNo, String ifsccode, String accounttype, String totalcharges, String cancelDate) {
        UserId = userId;
        AccountName = accountName;
        AccountNo = accountNo;
        this.ifsccode = ifsccode;
        Accounttype = accounttype;
        this.totalcharges = totalcharges;
        this.cancelDate = cancelDate;
    }

    public CancelOrderRefundModel() {
    }

    public String getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(String cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public String getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo(String accountNo) {
        AccountNo = accountNo;
    }

    public String getIfsccode() {
        return ifsccode;
    }

    public void setIfsccode(String ifsccode) {
        this.ifsccode = ifsccode;
    }

    public String getAccounttype() {
        return Accounttype;
    }

    public void setAccounttype(String accounttype) {
        Accounttype = accounttype;
    }

    public String getTotalcharges() {
        return totalcharges;
    }

    public void setTotalcharges(String totalcharges) {
        this.totalcharges = totalcharges;
    }
}
