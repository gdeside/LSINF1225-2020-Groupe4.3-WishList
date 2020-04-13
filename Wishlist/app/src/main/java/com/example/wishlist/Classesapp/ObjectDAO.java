package com.example.wishlist.Classesapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface ObjectDAO {

    @Insert
    Long insertTask(Object objet);

    @Update
    void updateTask(Object objet);

    @Delete
    void delete(Object objet);
}
