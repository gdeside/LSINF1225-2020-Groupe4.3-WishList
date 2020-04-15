package com.example.wishlist.Classesapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class},version = 1, exportSchema = false)
public abstract class ProductDatabase extends RoomDatabase {

    public abstract ProductDAO productDAO();

}
