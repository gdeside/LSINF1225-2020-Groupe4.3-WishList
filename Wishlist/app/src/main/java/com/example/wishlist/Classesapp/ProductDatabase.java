package com.example.wishlist.Classesapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

@Database(entities = {Product.class},version = 1, exportSchema = false)
public abstract class ProductDatabase extends RoomDatabase {

    private static ProductDatabase INSTANCE;

    public abstract ProductDAO productDAO();

    ///Pre populate Database
    public synchronized static ProductDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
            Prepopulate(Product.populateData());
        }
        return INSTANCE;
    }

    private static ProductDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                ProductDatabase.class,
                "productdb")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        /*Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getInstance(context).productDAO().insertAll(Product.populateData());
                            }
                        }); */
                    }
                })
                .build();
    }

    public static void Prepopulate(final Product[] products)
    {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
               INSTANCE.productDAO().insertAll(products);
                return null;
            }
        }.execute();
    }



}
