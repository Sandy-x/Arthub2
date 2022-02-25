package com.firstapp.arthub;

public class UserHelperClass {

    public UserHelperClass() {

    }

    String username,email,password;

    public UserHelperClass(String username, String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
