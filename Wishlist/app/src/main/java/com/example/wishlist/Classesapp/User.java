package com.example.wishlist.Classesapp;

public class User {
    public String ID;
    private String passeword;

    public User(String passeword) {
        this.passeword = passeword;
    }
    public String getID(){
        return this.ID;
    }
    public boolean connect(String Id,String passeword){
        return this.ID.equals(Id) && this.passeword.equals(passeword);
    }
}




