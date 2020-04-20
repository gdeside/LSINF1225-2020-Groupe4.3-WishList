package com.example.wishlist.Classesapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FriendDAO {

    @Insert
    Long insertTask(Friend friend);

    @Update
    void updateTask(Friend friend);

    @Delete
    void delete(Friend friend);

    @Query("Select * from friend where id_user == :ID")
    List<Friend> getID(String ID);

}
