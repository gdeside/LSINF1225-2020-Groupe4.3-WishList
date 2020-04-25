package com.example.wishlist.Classesapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ListAndUserDAO {

    @Insert
    Long insertTask(ListAndUser listAndUser);

    @Update
    void updateTask(ListAndUser listAndUser);

    @Delete
    void delete(ListAndUser listAndUser);

    @Query("select * from listAndUser")
    List<ListAndUser> getAll();

    @Query("Select * from  listAndUser where ID_User == :name")
    List<ListAndUser> getID(String name);

    @Query("Select * from  listAndUser where Num_List == :num_list ")
    List<ListAndUser> getListCreator(int num_list);
}
