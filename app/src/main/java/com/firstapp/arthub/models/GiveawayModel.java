package com.firstapp.arthub.models;

public class GiveawayModel {
    String Imageview,Url;

    public GiveawayModel() {
    }

    public GiveawayModel(String imageview, String url) {
        Imageview = imageview;
        Url = url;
    }

    public String getImageview() {
        return Imageview;
    }

    public void setImageview(String imageview) {
        Imageview = imageview;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
