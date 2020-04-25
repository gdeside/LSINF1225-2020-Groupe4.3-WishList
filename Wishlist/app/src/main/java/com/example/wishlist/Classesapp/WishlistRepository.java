package com.example.wishlist.Classesapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class WishlistRepository {

    private String DB_NAME = "wishlistdb";

    private WishlistDatabase wishlistDatabase;
    Context context;

    public WishlistRepository(Context context) {
        this.context = context;
        wishlistDatabase = Room.databaseBuilder(context, WishlistDatabase.class, DB_NAME).build();
    }

    public Long InsertTask(final Wishlist wishlist)
    {
        Long num_list = Integer.toUnsignedLong(0);
        try {
            num_list = new AsyncTask<Void, Void, Long>() {
                @Override
                protected Long doInBackground(Void... voids) {
                    return wishlistDatabase.wishlistDAO().insertTask(wishlist);
                }

                @Override
                protected void onPostExecute(Long result) {
                    super.onPostExecute(result);
                    Toast.makeText(context, wishlist.getName() + " is inserted", Toast.LENGTH_LONG).show();
                }


            }.execute().get();
        }
        catch (InterruptedException e) {
            e.printStackTrace(); //handle it the way you like
        } catch (ExecutionException e) {
            e.printStackTrace();//handle it the way you like
        }
        return num_list;

    }

    public List<Wishlist> getWishlists(){
        List<Wishlist> wishlistsList = wishlistDatabase.wishlistDAO().getAll();
        return  wishlistsList;
    }

    public List<Wishlist> getByID(int num){
        List<Wishlist> wishlistList = wishlistDatabase.wishlistDAO().getID(num);
        return wishlistList;
    }

    public void UpdateTask(final Wishlist wishlist)
    {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                wishlistDatabase.wishlistDAO().updateTask(wishlist);
                return null;
            }
        }.execute();
    }

    public void DeleteTask(final Wishlist wishlist)
    {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                wishlistDatabase.wishlistDAO().delete(wishlist);
                return null;
            }
        }.execute();
    }
}
