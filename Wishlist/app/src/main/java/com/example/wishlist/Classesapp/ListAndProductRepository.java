package com.example.wishlist.Classesapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListAndProductRepository {

    private String DB_NAME = "listAndProductdb";

    private ListAndProductDatabase listAndProductDatabase;
    Context context;

    public ListAndProductRepository(Context context) {
        this.context = context;
        listAndProductDatabase = Room.databaseBuilder(context, ListAndProductDatabase.class, DB_NAME).build();
    }

    public void InsertTask(final ListAndProduct listAndProduct)
    {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids){
                listAndProductDatabase.listAndProductDAO().insertTask(listAndProduct);
                return null;
            }

            @Override
            protected  void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                Toast.makeText(context," is inserted",Toast.LENGTH_LONG).show();
            }



        }.execute();

    }



    public List<ListAndProduct> getListAndProducts(){
        List<ListAndProduct> listAndProductList = listAndProductDatabase.listAndProductDAO().getAll();
        return  listAndProductList;
    }

    public List<String> getWishlistProductNames(int ListID){
        List<String> list = listAndProductDatabase.listAndProductDAO().getAllObject(ListID);
        return list;
    }
}
