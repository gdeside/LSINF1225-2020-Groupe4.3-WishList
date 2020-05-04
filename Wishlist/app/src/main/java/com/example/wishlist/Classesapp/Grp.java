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
    private Integer ID;

    @NonNull
    private String name;

    public Grp(Integer ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    @NonNull
    public Integer getID() {
        return ID;
    }

    public void setID(@NonNull Integer ID) {
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
