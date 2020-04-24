package com.example.wishlist.Classesapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutionException;

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

    ///Check if product name is used (Product Creation)
    /// return true if already used
    public Boolean isNameUsed(final String Name_Product)
    {
        Boolean FOO = false;
        try {
            FOO = new AsyncTask<Void, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(Void... voids) {
                    List<Product> userList = productDatabase.productDAO().getID(Name_Product);
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

    public List<Product> getProducts(){
        List<Product> productList = productDatabase.productDAO().getAll();
        return  productList;
    }

    public Product getProductByID(String name){
        List<Product> list = productDatabase.productDAO().getID(name);
        return list.get(0);
    }
}
