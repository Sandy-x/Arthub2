package com.firstapp.arthub.models;

public class freecompetetionModel {
    String Lastdate,Referenceimg,Backgroundimg,Topic,Prize,Description,Category;

    public freecompetetionModel() {
    }

    public freecompetetionModel(String lastdate, String referenceimg, String backgroundimg, String topic, String prize, String description, String category) {
        Lastdate = lastdate;
        Referenceimg = referenceimg;
        Backgroundimg = backgroundimg;
        Topic = topic;
        Prize = prize;
        Description = description;
        Category = category;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getLastdate() {
        return Lastdate;
    }

    public void setLastdate(String lastdate) {
        Lastdate = lastdate;
    }

    public String getReferenceimg() {
        return Referenceimg;
    }

    public void setReferenceimg(String referenceimg) {
        Referenceimg = referenceimg;
    }

    public String getBackgroundimg() {
        return Backgroundimg;
    }

    public void setBackgroundimg(String backgroundimg) {
        Backgroundimg = backgroundimg;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getPrize() {
        return Prize;
    }

    public void setPrize(String prize) {
        Prize = prize;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
