package com.example.wishlist.Classesapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class FriendRepository {

    private String DB_NAME = "frienddb";

    private FriendDatabase friendDatabase;
    Context context;

    public FriendRepository(Context context) {
        this.context = context;
        friendDatabase = Room.databaseBuilder(context, FriendDatabase.class, DB_NAME).build();
    }

    public void InsertTask(final Friend friend)
    {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids){
                friendDatabase.friendDAO().insertTask(friend);
                return null;
            }

            @Override
            protected  void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                Toast.makeText(context," is inserted",Toast.LENGTH_LONG).show();
            }



        }.execute();

    }





}
