package com.example.wishlist.Classesapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys ={"Num_List", "Product_Name"} )
public class ListAndProduct {

    @NonNull
    @ColumnInfo(name = "Num_List")
    private int num_list;

    @NonNull
    @ColumnInfo(name = "Product_Name")
    private String product_name;

    public ListAndProduct(int num_list, @NonNull String product_name) {
        this.num_list = num_list;
        this.product_name = product_name;
    }

    public int getNum_list() {
        return num_list;
    }

    public void setNum_list(int num_list) {
        this.num_list = num_list;
    }

    @NonNull
    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(@NonNull String product_name) {
        this.product_name = product_name;
    }
}
