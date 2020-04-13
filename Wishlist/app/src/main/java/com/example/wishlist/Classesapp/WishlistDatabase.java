package com.example.wishlist.Classesapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Wishlist.class},version = 1, exportSchema = false)
public abstract class WishlistDatabase extends RoomDatabase {

    public abstract WishlistDAO wishlistDAO();

}
