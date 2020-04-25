package com.example.wishlist.Classesapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys ={"ID_User", "ID_Ami"})
public class Friend {

    @NonNull
    @ColumnInfo(name = "ID_Ami")
    private String id_ami;

    @NonNull
    @ColumnInfo(name = "ID_User")
    private String id_user;

    @ColumnInfo(name = "Surname")
    private String surname;

    public Friend(@NonNull String id_ami, @NonNull String id_user, String surname) {
        this.id_ami = id_ami;
        this.id_user = id_user;
        this.surname = surname;
    }

    @NonNull
    public String getId_ami() {
        return id_ami;
    }

    public void setId_ami(@NonNull String id_ami) {
        this.id_ami = id_ami;
    }

    @NonNull
    public String getId_user() {
        return id_user;
    }

    public void setId_user(@NonNull String id_user) {
        this.id_user = id_user;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
