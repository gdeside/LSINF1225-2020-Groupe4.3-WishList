package com.example.wishlist.Classesapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

public class UserRepository {

    private String DB_NAME = "userdb";

    private UserDatabase userDatabase;
    Context context;

    public UserRepository(Context context) {
        this.context = context;
        userDatabase = Room.databaseBuilder(context, UserDatabase.class, DB_NAME).build();
    }

    public void InsertTask(final User user)
    {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids){
                userDatabase.userDAO().insertTask(user);
                return null;
            }

        }.execute();
    }
}
