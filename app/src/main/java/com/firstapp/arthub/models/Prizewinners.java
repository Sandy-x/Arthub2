package com.firstapp.arthub.models;

public class Prizewinners {
    String Firstprize,Secondprize,Thirdprize;

    public Prizewinners(String firstprize, String secondprize, String thirdprize) {
        Firstprize = firstprize;
        Secondprize = secondprize;
        Thirdprize = thirdprize;
    }

    public Prizewinners() {
    }

    public String getFirstprize() {
        return Firstprize;
    }

    public void setFirstprize(String firstprize) {
        Firstprize = firstprize;
    }

    public String getSecondprize() {
        return Secondprize;
    }

    public void setSecondprize(String secondprize) {
        Secondprize = secondprize;
    }

    public String getThirdprize() {
        return Thirdprize;
    }

    public void setThirdprize(String thirdprize) {
        Thirdprize = thirdprize;
    }
}
