package com.example.wishlist.Classesapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDAO {

    @Insert
    Long insertTask(Product product);

    @Insert
    void insertAll(Product... products);

    @Update
    void updateTask(Product product);

    @Delete
    void delete(Product product);

    @Query("select * from product")
    List<Product> getAll();

    @Query("Select * from product where Nom_Product == :name")
    List<Product> getID(String name);
}
