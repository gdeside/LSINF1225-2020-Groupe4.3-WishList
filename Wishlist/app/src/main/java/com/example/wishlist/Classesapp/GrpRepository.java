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

    public void InsertTask(final Grp grp)
    {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids){
                grpDatabase.grpDAO().insertTask(grp);
                return null;
            }

            @Override
            protected  void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                Toast.makeText(context," is inserted",Toast.LENGTH_LONG).show();
            }



        }.execute();
    }

    public void UpdateTask(final Grp grp)
    {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids){
                grpDatabase.grpDAO().updateTask(grp);
                return null;
            }

            @Override
            protected  void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                Toast.makeText(context,"is updated",Toast.LENGTH_LONG).show();
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

    ///Check if ID is used (Grp Creation)
    public Boolean isIDUsed(final String ID)
    {
        Boolean FOO = false;
        try {
            FOO = new AsyncTask<Void, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(Void... voids) {
                    List<Grp> grpList = grpDatabase.grpDAO().getID(ID);
                    return grpList.size() > 0;
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





    public List<Grp> getByID(final int ID)
    {
        List<Grp> grpList = grpDatabase.grpDAO().getID(ID);
        return grpList;
    }


    /*public List<Grp> getAllGrp(final String ID)
    {
        List<Grp> FOO = new ArrayList<>();
        try {
            FOO = new AsyncTask<Void, Void, List<Grp>>() {
                @Override
                protected List<Grp> doInBackground(Void... voids) {
                    List<Grp> grpList = grpDatabase.grpDAO().getAllGrp(ID);
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
    }*/





}
