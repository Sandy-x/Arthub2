package com.firstapp.arthub.models;

public class HelpandSupportModel {
    String Question,lin1,lin2,lin3,lin4,lin5,lin6,lin7,lin8;

    public HelpandSupportModel() {
    }

    public HelpandSupportModel(String question, String lin1, String lin2, String lin3, String lin4, String lin5, String lin6, String lin7, String lin8) {
        Question = question;
        this.lin1 = lin1;
        this.lin2 = lin2;
        this.lin3 = lin3;
        this.lin4 = lin4;
        this.lin5 = lin5;
        this.lin6 = lin6;
        this.lin7 = lin7;
        this.lin8 = lin8;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getLin1() {
        return lin1;
    }

    public void setLin1(String lin1) {
        this.lin1 = lin1;
    }

    public String getLin2() {
        return lin2;
    }

    public void setLin2(String lin2) {
        this.lin2 = lin2;
    }

    public String getLin3() {
        return lin3;
    }

    public void setLin3(String lin3) {
        this.lin3 = lin3;
    }

    public String getLin4() {
        return lin4;
    }

    public void setLin4(String lin4) {
        this.lin4 = lin4;
    }

    public String getLin5() {
        return lin5;
    }

    public void setLin5(String lin5) {
        this.lin5 = lin5;
    }

    public String getLin6() {
        return lin6;
    }

    public void setLin6(String lin6) {
        this.lin6 = lin6;
    }

    public String getLin7() {
        return lin7;
    }

    public void setLin7(String lin7) {
        this.lin7 = lin7;
    }

    public String getLin8() {
        return lin8;
    }

    public void setLin8(String lin8) {
        this.lin8 = lin8;
    }
}
