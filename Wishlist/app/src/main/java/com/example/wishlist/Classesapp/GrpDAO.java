package com.example.wishlist.Classesapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GrpDAO {

    @Insert
    Long insertTask(Grp grp);

    @Update
    void updateTask(Grp grp);

    @Delete
    void delete(Grp grp);

    @Query("Select * from grp where ID_Group == :id")
    List<Grp> getID(int id);


}
