package com.example.wishlist.Classesapp;

public class User {
    public String ID;
    private String password;

    public User(String password) {
        this.password = password;
    }
    public String getID(){
        return this.ID;
    }
    public boolean connect(String Id,String password){
        return this.ID.equals(Id) && this.password.equals(password);
    }
}




