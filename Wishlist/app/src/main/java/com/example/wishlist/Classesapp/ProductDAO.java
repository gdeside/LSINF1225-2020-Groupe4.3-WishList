package com.example.wishlist.Classesapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface ProductDAO {

    @Insert
    Long insertTask(Product product);

    @Update
    void updateTask(Product product);

    @Delete
    void delete(Product product);
}
