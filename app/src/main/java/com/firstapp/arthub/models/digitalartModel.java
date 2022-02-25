package com.firstapp.arthub.models;

public class digitalartModel {
    String Entry_fee;
    String Last_date;
    String Image_url;
    String Topic;
    String Compid;

    public digitalartModel() {
    }

    public digitalartModel(String entry_fee, String last_date, String image_url, String topic, String firstPrize, String secondPrize, String thirdPrize, String compid) {
        Entry_fee = entry_fee;
        Last_date = last_date;
        Image_url = image_url;
        Topic = topic;
        Compid = compid;
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
