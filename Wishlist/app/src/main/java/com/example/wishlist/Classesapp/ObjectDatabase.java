package com.example.wishlist.Classesapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Object.class},version = 1, exportSchema = false)
public abstract class ObjectDatabase extends RoomDatabase {

    public abstract ObjectDAO objectDAO();

}
