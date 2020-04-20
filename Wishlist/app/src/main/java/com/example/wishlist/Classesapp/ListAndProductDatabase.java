package com.example.wishlist.Classesapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ListAndProduct.class},version = 1, exportSchema = false)
public abstract class ListAndProductDatabase extends RoomDatabase {

    public abstract ListAndProductDAO listAndProductDAO();
}
