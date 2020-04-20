package com.example.wishlist.Classesapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Friend.class},version = 1, exportSchema = false)
public abstract class FriendDatabase extends RoomDatabase {

    public abstract FriendDAO friendDAO();
}
