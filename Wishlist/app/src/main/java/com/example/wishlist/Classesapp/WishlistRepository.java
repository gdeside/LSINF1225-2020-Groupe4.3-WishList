package com.example.wishlist.Classesapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import java.util.List;

public class WishlistRepository {

    private String DB_NAME = "wishlistdb";

    private WishlistDatabase wishlistDatabase;
    Context context;

    public WishlistRepository(Context context) {
        this.context = context;
        wishlistDatabase = Room.databaseBuilder(context, WishlistDatabase.class, DB_NAME).build();
    }

    public void InsertTask(final Wishlist wishlist)
    {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids){
                wishlistDatabase.wishlistDAO().insertTask(wishlist);
                return null;
            }

            @Override
            protected  void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                Toast.makeText(context,wishlist.getName()+" is inserted",Toast.LENGTH_LONG).show();
            }



        }.execute();

    }

    public List<Wishlist> getWishlists(){
        List<Wishlist> wishlistsList = wishlistDatabase.wishlistDAO().getAll();
        return  wishlistsList;
    }
}
