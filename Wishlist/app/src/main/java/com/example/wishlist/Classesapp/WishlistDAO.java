package com.example.wishlist.Classesapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WishlistDAO {

    @Insert
    Long insertTask(Wishlist wishlist);

    @Update
    void updateTask(Wishlist wishlist);

    @Delete
    void delete(Wishlist wishlist);

    @Query("select * from wishlist")
    List<Wishlist> getAll();
}
