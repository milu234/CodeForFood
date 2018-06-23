package com.example.milan.codeforfood;

public class UserInformation {
    public String name;
    public String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public UserInformation(){

    }

    public UserInformation(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }
}
