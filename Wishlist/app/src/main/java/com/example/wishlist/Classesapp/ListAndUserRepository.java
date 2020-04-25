package com.example.wishlist.Classesapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListAndUserRepository {

    private String DB_NAME = "listAndUserdb";

    private ListAndUserDatabase listAndUserDatabase;
    Context context;

    public ListAndUserRepository(Context context) {
        this.context = context;
        listAndUserDatabase = Room.databaseBuilder(context, ListAndUserDatabase.class, DB_NAME).build();
    }

    public void InsertTask(final ListAndUser listAndUser)
    {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids){
                listAndUserDatabase.listAndUserDAO().insertTask(listAndUser);
                return null;
            }

            @Override
            protected  void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                Toast.makeText(context," is inserted",Toast.LENGTH_LONG).show();
            }



        }.execute();

    }



    public List<ListAndUser> getListAndUsers(){
        List<ListAndUser> listAndUserList = listAndUserDatabase.listAndUserDAO().getAll();
        return  listAndUserList;
    }

    public List<ListAndUser> getIDUser(String id)
    {
        List<ListAndUser> listAndUserList = listAndUserDatabase.listAndUserDAO().getID(id);
        return listAndUserList;
    }


}
