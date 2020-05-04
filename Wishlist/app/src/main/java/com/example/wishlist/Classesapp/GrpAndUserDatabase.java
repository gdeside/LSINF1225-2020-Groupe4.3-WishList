package com.example.wishlist.Classesapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {GrpAndUser.class},version = 1, exportSchema = false)
public abstract class GrpAndUserDatabase extends RoomDatabase {

    public abstract GrpAndUserDAO grpAndUserDAO();
}
