package com.firstapp.arthub.models;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class All_user_competetionlists {
    private String RegisteredID,Comp_id,Email, Name,Fees,LastDate,Category,PaymentStatus,Topic,Poster_url,Comp_ImageUrl,RegisteredDate;


    public All_user_competetionlists() {
    }


    public All_user_competetionlists(String registeredID, String comp_id, String email, String name, String fees, String lastDate, String category, String paymentStatus, String topic, String poster_url, String comp_ImageUrl, String registeredDate) {
        RegisteredID = registeredID;
        Comp_id = comp_id;
        Email = email;
        Name = name;
        Fees = fees;
        LastDate = lastDate;
        Category = category;
        PaymentStatus = paymentStatus;
        Topic = topic;
        Poster_url = poster_url;
        Comp_ImageUrl = comp_ImageUrl;
        RegisteredDate = registeredDate;
    }

    public String getRegisteredID() {
        return RegisteredID;
    }

    public void setRegisteredID(String registeredID) {
        RegisteredID = registeredID;
    }

    public String getComp_id() {
        return Comp_id;
    }

    public void setComp_id(String comp_id) {
        Comp_id = comp_id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFees() {
        return Fees;
    }

    public void setFees(String fees) {
        Fees = fees;
    }

    public String getLastDate() {
        return LastDate;
    }

    public void setLastDate(String lastDate) {
        LastDate = lastDate;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        PaymentStatus = paymentStatus;
    }

    public String getPoster_url() {
        return Poster_url;
    }

    public void setPoster_url(String poster_url) {
        Poster_url = poster_url;
    }

    public String getComp_ImageUrl() {
        return Comp_ImageUrl;
    }

    public void setComp_ImageUrl(String comp_ImageUrl) {
        Comp_ImageUrl = comp_ImageUrl;
    }

    public String getRegisteredDate() {
        return RegisteredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        RegisteredDate = registeredDate;
    }
}
