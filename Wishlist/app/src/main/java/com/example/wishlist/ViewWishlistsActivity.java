package com.example.wishlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.wishlist.Classesapp.Wishlist;
import com.example.wishlist.Classesapp.WishlistRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ViewWishlistsActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    WishlistAdapter wishlistAdapter;
    ArrayList<Wishlist> wishlistArrayList, wishlistArrayList_search;
    EditText edt_wishlist_search;

  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_wishlists);

        recyclerView = (RecyclerView)findViewById(R.id.Wishlist_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        edt_wishlist_search = findViewById(R.id.edt_wishlist_search);

        edt_wishlist_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text=s.toString();
                Filter(text);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
            wishlistArrayList_search = new ArrayList<>();

            for(int i =0; i <wishlistList.size();i++)
            {
                wishlistArrayList.add(wishlistList.get(i));
                wishlistArrayList_search.add(wishlistList.get(i));
            }


            return null;
        }



        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);

            wishlistAdapter = new WishlistAdapter(wishlistArrayList, ViewWishlistsActivity.this);
            recyclerView.setAdapter(wishlistAdapter);
        }
    }


    ///Filter wishlists by name
    public void Filter(String charText)
    {
        charText = charText.toLowerCase(Locale.getDefault());

        wishlistArrayList.clear();

        if(charText.length() == 0)
            wishlistArrayList.addAll(wishlistArrayList_search);
        else
        {
            for( Wishlist wishlist : wishlistArrayList_search)
            {
                if(wishlist.getName().toString().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    wishlistArrayList.add(wishlist);
                }
            }
            wishlistAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        new LoadDataTask().execute();
    }
}
