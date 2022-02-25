package com.firstapp.arthub.models;

public class All_Users {
    private String Email , Username , Password ;
    private long walletsmoney=0;
    private long trophy=0;

    public All_Users() {
    }

    public All_Users(String email, String username, String password) {
        Email = email;
        Username = username;
        Password = password;
    }

    public long getTrophy() {
        return trophy;
    }

    public void setTrophy(long trophy) {
        this.trophy = trophy;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public long getWalletsmoney() {
        return walletsmoney;
    }

    public void setWalletsmoney(long walletsmoney) {
        this.walletsmoney = walletsmoney;
    }
}
