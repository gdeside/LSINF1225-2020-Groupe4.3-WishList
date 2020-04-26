package com.example.wishlist.Classesapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class FriendRepository {

    private String DB_NAME = "frienddb";

    private FriendDatabase friendDatabase;
    Context context;

    public FriendRepository(Context context) {
        this.context = context;
        friendDatabase = Room.databaseBuilder(context, FriendDatabase.class, DB_NAME).build();
    }

    public void InsertTask(final Friend friend)
    {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids){
                friendDatabase.friendDAO().insertTask(friend);
                return null;
            }

            @Override
            protected  void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                Toast.makeText(context," is inserted",Toast.LENGTH_LONG).show();
            }



        }.execute();
    }

    public void UpdateTask(final Friend friend)
    {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids){
                friendDatabase.friendDAO().updateTask(friend);
                return null;
            }

            @Override
            protected  void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                Toast.makeText(context,"is updated",Toast.LENGTH_LONG).show();
            }



        }.execute();

    }


    public Boolean isAlreadyFriend(final String ID_User, final String ID_Friend)
    {
        Boolean FOO = false;
        try {
            FOO = new AsyncTask<Void, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(Void... voids) {
                    List<Friend> friendList = friendDatabase.friendDAO().getID(ID_User,ID_Friend);
                    return friendList.size() > 0;
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


    public List<Friend> getAllFriend(String username)
    {
        List<Friend> friendList = friendDatabase.friendDAO().getAllFriend(username);
        return friendList;
    }





}
