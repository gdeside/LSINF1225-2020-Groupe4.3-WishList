package com.example.wishlist.Classesapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class GrpAndUserRepository {

    private String DB_NAME = "grpAndUserdb";

    private GrpAndUserDatabase grpAndUserDatabase;
    Context context;

    public GrpAndUserRepository(Context context) {
        this.context = context;
        grpAndUserDatabase = Room.databaseBuilder(context, GrpAndUserDatabase.class, DB_NAME).build();
    }

    public void InsertTask(final GrpAndUser grpAndUser)
    {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids){
                grpAndUserDatabase.grpAndUserDAO().insertTask(grpAndUser);
                return null;
            }

            @Override
            protected  void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                Toast.makeText(context," is inserted",Toast.LENGTH_LONG).show();
            }



        }.execute();
    }

    public void UpdateTask(final GrpAndUser grpAndUser)
    {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids){
                grpAndUserDatabase.grpAndUserDAO().updateTask(grpAndUser);
                return null;
            }

            @Override
            protected  void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                Toast.makeText(context,"is updated",Toast.LENGTH_LONG).show();
            }



        }.execute();
    }

    public void DeleteTask(final GrpAndUser grpAndUser) {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                grpAndUserDatabase.grpAndUserDAO().delete(grpAndUser);
                return null;
            }
        }.execute();
    }





    public List<GrpAndUser> getUsersByID(final int ID)
    {
        List<GrpAndUser> grpAndUserList = grpAndUserDatabase.grpAndUserDAO().getUsersByID(ID);
        return grpAndUserList;
    }

    public List<GrpAndUser> getAllGrpAndUser_unsync(final String ID)
    {
        List<GrpAndUser> grpAndUserList = grpAndUserDatabase.grpAndUserDAO().getGroupsByUser(ID);
        return grpAndUserList;
    }


    public List<GrpAndUser> getAllGrpAndUser(final String ID)
    {
        List<GrpAndUser> FOO = new ArrayList<>();
       try {
            FOO = new AsyncTask<Void, Void, List<GrpAndUser>>() {
                @Override
                protected List<GrpAndUser> doInBackground(Void... voids) {
                    List<GrpAndUser> grpAndUserList = grpAndUserDatabase.grpAndUserDAO().getGroupsByUser(ID);
                    return grpAndUserList;
                }

                @Override
                protected void onPostExecute(List<GrpAndUser> result) {
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


    public int getSizeGroup(final int ID)
    {
        int FOO = 0;
        try {
            FOO = new AsyncTask<Void, Void, Integer>() {
                @Override
                protected Integer doInBackground(Void... voids) {
                    List<GrpAndUser> grpAndUserList = grpAndUserDatabase.grpAndUserDAO().getUsersByID(ID);
                    return grpAndUserList.size();
                }

                @Override
                protected void onPostExecute(Integer result) {
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
