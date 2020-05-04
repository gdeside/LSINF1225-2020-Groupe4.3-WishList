package com.example.wishlist.Classesapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Grp.class},version = 1, exportSchema = false)
public abstract class GrpDatabase extends RoomDatabase {

    public abstract GrpDAO grpDAO();
}
