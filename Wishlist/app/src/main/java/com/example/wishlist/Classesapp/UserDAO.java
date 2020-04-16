package com.example.wishlist.Classesapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    Long insertTask(User user);

    @Update
    void updateTask(User user);

    @Delete
    void delete(User user);

    @Query("Select * from user where id == :ID")
    List<User> getID(String ID);

    @Query("Select * from user where id == :ID and mot_de_passe == :PASSWORD")
    List<User> login(String ID, String PASSWORD);
}
