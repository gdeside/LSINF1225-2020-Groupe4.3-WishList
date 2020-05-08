package com.example.wishlist.Classesapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class GrpRepository {

    private String DB_NAME = "grpdb";

    private GrpDatabase grpDatabase;
    Context context;

    public GrpRepository(Context context) {
        this.context = context;
        grpDatabase = Room.databaseBuilder(context, GrpDatabase.class, DB_NAME).build();
    }

   public Long InsertTask(final Grp grp)
    {
        Long foo = Integer.toUnsignedLong(0);
        try {
            foo = new AsyncTask<Void, Void, Long>() {
                @Override
                protected Long doInBackground(Void... voids) {
                    return grpDatabase.grpDAO().insertTask(grp);
                }

                @Override
                protected void onPostExecute(Long result) {
                    super.onPostExecute(result);
                    Toast.makeText(context, " is inserted", Toast.LENGTH_LONG).show();
                }


            }.execute().get();
        }
        catch (InterruptedException e) {
            e.printStackTrace(); //handle it the way you like
        } catch (ExecutionException e) {
            e.printStackTrace();//handle it the way you like
        }
        return foo;
    }



    public void UpdateTask(final Grp grp)
    {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids){
                grpDatabase.grpDAO().updateTask(grp);
                return null;
            }
        }.execute();
    }

    public void DeleteTask(final Grp grp) {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                grpDatabase.grpDAO().delete(grp);
                return null;
            }
        }.execute();
    }


    public Grp getByID(final int ID)
    {
        List<Grp> grpList = grpDatabase.grpDAO().getID(ID);
        return grpList.get(0);
    }


    public List<Grp> getAllGrp(final int ID)
    {
        List<Grp> FOO = new ArrayList<>();
        try {
            FOO = new AsyncTask<Void, Void, List<Grp>>() {
                @Override
                protected List<Grp> doInBackground(Void... voids) {
                    List<Grp> grpList = grpDatabase.grpDAO().getID(ID);
                    return grpList;
                }

                @Override
                protected void onPostExecute(List<Grp> result) {
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





}
