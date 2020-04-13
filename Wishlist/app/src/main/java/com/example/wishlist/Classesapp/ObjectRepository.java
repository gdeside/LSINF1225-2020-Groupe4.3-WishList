package com.example.wishlist.Classesapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

public class ObjectRepository {

    private String DB_NAME = "objectdb";

    private ObjectDatabase objectDatabase;
    Context context;

    public UserRepository(Context context) {
        this.context = context;
        objectDatabase = Room.databaseBuilder(context, ObjectDatabase.class, DB_NAME).build();
    }

    public void InsertTask(final Object object)
    {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids){
                objectDatabase.objectDAO().insertTask(object);
                return null;
            }

            @Override
            protected  void onPostExecute(void aVoid){
                super.onPostExecute(aVoid);
                Toast.makeText(context,object.getName()+" is inserted",Toast.LENGTH_LONG).show();
            }



        }.execute();

    }
}
