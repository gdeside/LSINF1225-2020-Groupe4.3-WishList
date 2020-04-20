package com.example.wishlist.Classesapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ListAndUser {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "Num_List")
    private int num_list;

    @NonNull
    @ColumnInfo(name = "ID_User")
    private String id_user;

    public ListAndUser(int num_list, @NonNull String id_user) {
        this.num_list = num_list;
        this.id_user = id_user;
    }

    public int getNum_list() {
        return num_list;
    }

    public void setNum_list(int num_list) {
        this.num_list = num_list;
    }

    @NonNull
    public String getId_user() {
        return id_user;
    }

    public void setId_user(@NonNull String id_user) {
        this.id_user = id_user;
    }
}
