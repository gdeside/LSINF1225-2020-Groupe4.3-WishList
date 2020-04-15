package com.example.wishlist.Classesapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

public class ProductRepository {

    private String DB_NAME = "productdb";

    private ProductDatabase productDatabase;
    Context context;

    public ProductRepository(Context context) {
        this.context = context;
        productDatabase = Room.databaseBuilder(context, ProductDatabase.class, DB_NAME).build();
    }

    public void InsertTask(final Product product)
    {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids){
                productDatabase.productDAO().insertTask(product);
                return null;
            }

            @Override
            protected  void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                Toast.makeText(context,product.getName()+" is inserted",Toast.LENGTH_LONG).show();
            }



        }.execute();

    }
}
