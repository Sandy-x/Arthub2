package com.firstapp.arthub.models;

public class PaintingSecondModel {
    String Topic;
    String Fees;
    String LastDate;
    String Poster_url;
    String registeredDate;

    public PaintingSecondModel() {
    }

    public PaintingSecondModel(String topic, String fees, String lastDate, String poster_url, String registeredDate) {
        Topic = topic;
        Fees = fees;
        LastDate = lastDate;
        Poster_url = poster_url;
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

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }
}
