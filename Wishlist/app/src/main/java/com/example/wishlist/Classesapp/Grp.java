package com.example.wishlist.Classesapp;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Grp {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_Group")
    private int ID;

    @NonNull
    private String name;

    public Grp(@NonNull String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }
}
