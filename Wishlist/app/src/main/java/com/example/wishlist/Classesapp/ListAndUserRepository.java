package com.example.wishlist.Classesapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListAndUserRepository {

    private String DB_NAME = "listAndUserdb";

    private ListAndUserDatabase listAndUserDatabase;
    Context context;

    public ListAndUserRepository(Context context) {
        this.context = context;
        listAndUserDatabase = Room.databaseBuilder(context, ListAndUserDatabase.class, DB_NAME).build();
    }

    public void InsertTask(final ListAndUser listAndUser)
    {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids){
                listAndUserDatabase.listAndUserDAO().insertTask(listAndUser);
                return null;
            }

            @Override
            protected  void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                Toast.makeText(context," is inserted",Toast.LENGTH_LONG).show();
            }



        }.execute();

    }



    public List<ListAndUser> getListAndUsers(){
        List<ListAndUser> listAndUserList = listAndUserDatabase.listAndUserDAO().getAll();
        return  listAndUserList;
    }

    ///Return All List from an user
    public List<ListAndUser> getIDUser(String id)
    {
        List<ListAndUser> listAndUserList = listAndUserDatabase.listAndUserDAO().getID(id);
        return listAndUserList;
    }



    ///Return true if there is no user with this id + this password
    public Boolean isCreator(final String username, final int Num_list)
    {
        Boolean FOO = false;
        try {
            FOO = new  AsyncTask<Void, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(Void... voids) {
                    List<ListAndUser> listAndUserList = listAndUserDatabase.listAndUserDAO().getList(Num_list);
                    String foo = listAndUserList.get(0).getId_user();
                    return foo.equals(username);
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

    ///Return true if there is no user with this id + this password
    public String showCreator(final int Num_list)
    {
        String FOO = "";
        try {
            FOO = new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void... voids) {
                    List<ListAndUser> listAndUserList = listAndUserDatabase.listAndUserDAO().getList(Num_list);

                    return listAndUserList.get(0).getId_user();
                }

                @Override
                protected void onPostExecute(String result) {
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

    public void DeleteWishlist(final int wishlist_num)
    {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                List<ListAndUser> listAndUserList = listAndUserDatabase.listAndUserDAO().getList(wishlist_num);
                for(ListAndUser listAndUser : listAndUserList)
                    listAndUserDatabase.listAndUserDAO().delete(listAndUser);
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
            }
        }.execute();
    }



    public List<ListAndUser> getAllList(final String ID)
    {
        List<ListAndUser> FOO = new ArrayList<>();
        try {
            FOO = new AsyncTask<Void, Void, List<ListAndUser>>() {
                @Override
                protected List<ListAndUser>doInBackground(Void... voids) {
                    List<ListAndUser> listAndUserList = listAndUserDatabase.listAndUserDAO().getID(ID);
                    return listAndUserList;
                }

                @Override
                protected void onPostExecute(List<ListAndUser> result) {
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
