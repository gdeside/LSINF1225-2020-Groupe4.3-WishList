package com.example.wishlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.wishlist.Classesapp.Wishlist;
import com.example.wishlist.Classesapp.WishlistRepository;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    //////// A Supprimer ?

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    ArrayList<Wishlist> wishlistArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        recyclerView = (RecyclerView)findViewById(R.id.Wishlist_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        new LoadDataTask().execute();



    }

    class LoadDataTask extends AsyncTask<Void,Void,Void>
    {
        WishlistRepository wishlistRepository;
        List<Wishlist> wishlistList;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            wishlistRepository= new WishlistRepository(getApplicationContext());
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            wishlistList = wishlistRepository.getWishlists();
            wishlistArrayList = new ArrayList<>();

            for(int i =0; i <wishlistList.size();i++)
            {
                wishlistArrayList.add(wishlistList.get(i));
            }


            return null;
        }



        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);

            WishlistAdapter wishlistAdapter = new WishlistAdapter(wishlistArrayList, ViewActivity.this);
            recyclerView.setAdapter(wishlistAdapter);
        }


    }
}
