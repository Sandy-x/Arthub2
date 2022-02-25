package com.firstapp.arthub.models;

public class feedbackModel {
    String Feedback;

    public feedbackModel() {
    }

    public feedbackModel(String feedback) {
        Feedback = feedback;
    }

    public String getFeedback() {
        return Feedback;
    }

    public void setFeedback(String feedback) {
        Feedback = feedback;
    }

}
