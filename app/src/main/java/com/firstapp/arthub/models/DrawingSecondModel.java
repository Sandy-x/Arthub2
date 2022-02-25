package com.firstapp.arthub.models;

public class DrawingSecondModel {
     String Topic;
     String Fees;
     String LastDate;
     String Poster_url;
     String Comp_id;
     String RegisteredID;
     String registeredDate;

    public DrawingSecondModel() {
    }

    public DrawingSecondModel(String topic, String fees, String lastDate, String poster_url, String comp_id, String registeredID, String registeredDate) {
        Topic = topic;
        Fees = fees;
        LastDate = lastDate;
        Poster_url = poster_url;
        Comp_id = comp_id;
        RegisteredID = registeredID;
        this.registeredDate = registeredDate;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
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

    public String getPoster_url() {
        return Poster_url;
    }

    public void setPoster_url(String poster_url) {
        Poster_url = poster_url;
    }

    public String getComp_id() {
        return Comp_id;
    }

    public void setComp_id(String comp_id) {
        Comp_id = comp_id;
    }

    public String getRegisteredID() {
        return RegisteredID;
    }

    public void setRegisteredID(String registeredID) {
        RegisteredID = registeredID;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }
}
