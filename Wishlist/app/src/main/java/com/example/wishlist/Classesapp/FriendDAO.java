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

    @Query("Select * from friend where ID_User == :ID_User and ID_Ami = :ID_Friend")
    List<Friend> getID(String ID_User, String ID_Friend);

    @Query("Select * from friend where ID_User == :ID_User")
    List<Friend> getAllFriend(String ID_User);

}
