package com.sara_nabil.social_eat_login;

public class UserDetailes {
    String name ;
    String address ;
    String email ;
    int phone ;
    String gender ;

    public UserDetailes(String name, String address, String email, int phone, String gender) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

}
