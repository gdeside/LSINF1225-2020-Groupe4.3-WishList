package com.example.wishlist.Classesapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface UserDAO {

    @Insert
    Long insertTask(User user);

    @Update
    void updateTask(User user);

    @Delete
    void delete(User user);
}
