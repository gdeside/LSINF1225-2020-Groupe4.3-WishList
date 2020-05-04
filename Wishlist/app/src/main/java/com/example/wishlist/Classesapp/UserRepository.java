package com.example.wishlist.Classesapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserRepository {

    private String DB_NAME = "userdb";

    private UserDatabase userDatabase;
    Context context;

    public UserRepository(Context context) {
        this.context = context;
        userDatabase = Room.databaseBuilder(context, UserDatabase.class, DB_NAME).build();
    }

    public void InsertTask(final User user)
    {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids){
                userDatabase.userDAO().insertTask(user);
                return null;
            }

            @Override
            protected  void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                Toast.makeText(context,user.getName()+" is inserted",Toast.LENGTH_LONG).show();
            }



        }.execute();
    }

    public void DeleteTask(final User user) {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                userDatabase.userDAO().delete(user);
                return null;
            }
        }.execute();
    }

    public void UpdateTask(final User user)
    {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                userDatabase.userDAO().updateTask(user);
                return null;
            }
        }.execute();
    }

    ///Check if ID is used (User Creation)
    public Boolean isIDUsed(final String ID)
    {
        Boolean FOO = false;
        try {
             FOO = new AsyncTask<Void, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(Void... voids) {
                    List<User> userList = userDatabase.userDAO().getID(ID);
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

    ///Return true if there is no user with this id + this password
    public Boolean CheckLogin(final String ID, final String PASSWORD)
    {
        Boolean FOO = false;
        try {
            FOO = new AsyncTask<Void, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(Void... voids) {
                    List<User> userList = userDatabase.userDAO().login(ID,PASSWORD);
                    return userList.size() == 0;
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

    public User getUserByID(final String ID)
    {
        User FOO = new User("A","A","A","A"); /// wont be inserted anyway
        try {
            FOO = new AsyncTask<Void, Void, User>() {
                @Override
                protected User doInBackground(Void... voids) {
                    List<User> userList = userDatabase.userDAO().getID(ID);
                    return userList.get(0);
                }

                @Override
                protected void onPostExecute(User result) {
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
