package com.example.wishlist.Classesapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys ={"ID_User", "ID_Grp"} )
public class GrpAndUser {
    @NonNull
    @ColumnInfo(name = "ID_User")
    private String id_user;

    @NonNull
    @ColumnInfo(name = "ID_Grp")
    private int id_grp;

    public GrpAndUser(@NonNull String id_user, int id_grp) {
        this.id_user = id_user;
        this.id_grp = id_grp;
    }

    @NonNull
    public String getId_user() {
        return id_user;
    }

    public void setId_user(@NonNull String id_user) {
        this.id_user = id_user;
    }

    public int getId_grp() {
        return id_grp;
    }

    public void setId_grp(int id_grp) {
        this.id_grp = id_grp;
    }
}
