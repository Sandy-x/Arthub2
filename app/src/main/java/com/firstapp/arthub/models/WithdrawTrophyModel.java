package com.firstapp.arthub.models;

public class WithdrawTrophyModel {
    String Status,IssuedDate,RequestedTrophy,AvailableTrophy;

    public WithdrawTrophyModel() {
    }

    public WithdrawTrophyModel(String status,String issuedDate, String requestedTrophy, String availableTrophy) {
        IssuedDate = issuedDate;
        Status = status;
        RequestedTrophy = requestedTrophy;
        AvailableTrophy = availableTrophy;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getIssuedDate() {
        return IssuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        IssuedDate = issuedDate;
    }

    public String getRequestedTrophy() {
        return RequestedTrophy;
    }

    public void setRequestedTrophy(String requestedTrophy) {
        RequestedTrophy = requestedTrophy;
    }

    public String getAvailableTrophy() {
        return AvailableTrophy;
    }

    public void setAvailableTrophy(String availableTrophy) {
        AvailableTrophy = availableTrophy;
    }
}
