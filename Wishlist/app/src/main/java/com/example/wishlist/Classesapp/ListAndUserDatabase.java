package com.example.wishlist.Classesapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ListAndUser.class},version = 1, exportSchema = false)
public abstract class ListAndUserDatabase extends RoomDatabase {

    public abstract ListAndUserDAO listAndUserDAO();
}
