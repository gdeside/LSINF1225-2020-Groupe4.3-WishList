package com.example.wishlist.Classesapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ListAndProductDAO {

    @Insert
    Long insertTask(ListAndProduct listAndProduct);

    @Update
    void updateTask(ListAndProduct listAndProduct);

    @Delete
    void delete(ListAndProduct listAndProduct);

    @Query("select * from listAndProduct")
    List<ListAndProduct> getAll();

    @Query("Select * from ListAndProduct where Num_List == :id")
    List<ListAndProduct> getObjectList(int id);

    @Query("Select Product_Name from ListAndProduct where Num_List == :id")
    List<String> getAllObject(int id);
}
