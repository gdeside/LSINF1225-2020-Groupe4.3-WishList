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

    ///Check if product already in it
    public Boolean isObjectInList(final String ID,final int Num_list)
    {
        Boolean FOO = false;
        try {
            FOO = new AsyncTask<Void, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(Void... voids) {
                    List<ListAndProduct> userList = listAndProductDatabase.listAndProductDAO().getid(ID,Num_list);
                    return userList.size() > 0;
                }

                @Override
                protected void onPostExecute(Boolean result) {
                    super.onPostExecute(result);

                }
            }.execute().get();
        }
        catch (InterruptedException e) {
            e.printStackTrace(); //handle it the way you like
        } catch (ExecutionException e) {
            e.printStackTrace();//handle it the way you like
        }
        return FOO;
    }

    public void DeleteTask(final ListAndProduct listAndProduct)
    {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                listAndProductDatabase.listAndProductDAO().delete(listAndProduct);
                return null;
            }
        }.execute();
    }
}
