package com.example.wishlist.Classesapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GrpAndUserDAO {

    @Insert
    Long insertTask(GrpAndUser grpAndUser);

    @Update
    void updateTask(GrpAndUser grpAndUser);

    @Delete
    void delete(GrpAndUser grpAndUser);

    @Query("Select * from grpAndUser where ID_Grp == :id_grp")
    List<GrpAndUser> getUsersByID(int id_grp);


}
