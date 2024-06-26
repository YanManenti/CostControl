package com.example.costcontrol.persistance.models;

import java.util.Random;

public class User {

    private Integer id;
    private String email;
    private String password;

    public User(){
    }
    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.id = new Random().nextInt();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
