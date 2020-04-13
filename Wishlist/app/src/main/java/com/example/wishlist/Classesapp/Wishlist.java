package com.example.wishlist.Classesapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Wishlist {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "Num_liste")
    private int num_list;

    @ColumnInfo(name = "Nom_liste")
    private String name;

    @ColumnInfo(name = "Option")
    private Boolean option;

    @ColumnInfo(name = "Description")
    private String description;


    public Wishlist(int num_list, String name, Boolean option, String description) {
        this.num_list = num_list;
        this.name = name;
        this.option = option;
        this.description = description;
    }

    public int getNum_list() {
        return num_list;
    }

    public void setNum_list(int num_list) {
        this.num_list = num_list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOption() {
        return option;
    }

    public void setOption(Boolean option) {
        this.option = option;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
