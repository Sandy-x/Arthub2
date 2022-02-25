package com.firstapp.arthub.models;

public class freeCompModel {
    String Name,Email,Instagramid,Category,Topic,LastDate,ReferenceImage;

    public freeCompModel() {
    }

    public freeCompModel(String name, String email, String instagramid, String category, String topic, String lastDate, String referenceImage) {
        Name = name;
        Email = email;
        Instagramid = instagramid;
        Category = category;
        Topic = topic;
        LastDate = lastDate;
        ReferenceImage = referenceImage;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getInstagramid() {
        return Instagramid;
    }

    public void setInstagramid(String instagramid) {
        Instagramid = instagramid;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getLastDate() {
        return LastDate;
    }

    public void setLastDate(String lastDate) {
        LastDate = lastDate;
    }

    public String getReferenceImage() {
        return ReferenceImage;
    }

    public void setReferenceImage(String referenceImage) {
        ReferenceImage = referenceImage;
    }
}
