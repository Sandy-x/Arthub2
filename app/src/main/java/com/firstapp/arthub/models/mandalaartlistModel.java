package com.firstapp.arthub.models;

public class mandalaartlistModel {
    String Entry_fee;
    String Last_date;
    String Image_url;
    String Topic;
    String FirstPrize;
    String SecondPrize;
    String ThirdPrize;
    String Compid;

    public mandalaartlistModel() {
    }

    public mandalaartlistModel(String entry_fee, String last_date, String image_url, String topic, String firstPrize, String secondPrize, String thirdPrize, String compid) {
        Entry_fee = entry_fee;
        Last_date = last_date;
        Image_url = image_url;
        Topic = topic;
        FirstPrize = firstPrize;
        SecondPrize = secondPrize;
        ThirdPrize = thirdPrize;
        Compid = compid;
    }

    public String getFirstPrize() {
        return FirstPrize;
    }

    public void setFirstPrize(String firstPrize) {
        FirstPrize = firstPrize;
    }

    public String getSecondPrize() {
        return SecondPrize;
    }

    public void setSecondPrize(String secondPrize) {
        SecondPrize = secondPrize;
    }

    public String getThirdPrize() {
        return ThirdPrize;
    }

    public void setThirdPrize(String thirdPrize) {
        ThirdPrize = thirdPrize;
    }

    public String getCompid() {
        return Compid;
    }

    public void setCompid(String compid) {
        Compid = compid;
    }

    public String getEntry_fee() {
        return Entry_fee;
    }

    public void setEntry_fee(String entry_fee) {
        Entry_fee = entry_fee;
    }

    public String getLast_date() {
        return Last_date;
    }

    public void setLast_date(String last_date) {
        Last_date = last_date;
    }

    public String getImage_url() {
        return Image_url;
    }

    public void setImage_url(String image_url) {
        Image_url = image_url;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }
}
