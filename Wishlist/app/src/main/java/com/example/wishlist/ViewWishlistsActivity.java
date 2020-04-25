package com.example.wishlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.wishlist.Classesapp.Friend;
import com.example.wishlist.Classesapp.FriendRepository;
import com.example.wishlist.Classesapp.ListAndUser;
import com.example.wishlist.Classesapp.ListAndUserRepository;
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

        ////To see Cards
        recyclerView = (RecyclerView)findViewById(R.id.Wishlist_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ///Search bar
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
        FriendRepository friendRepository;
        ListAndUserRepository listAndUserRepository;
        List<Wishlist> wishlistList;
        List<Friend> friendList;
        ArrayList<ListAndUser> listAndUserArrayList;
        ArrayList<Integer> WishlistNumArrayList;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            wishlistRepository= new WishlistRepository(getApplicationContext());
            friendRepository = new FriendRepository(getApplicationContext());
            listAndUserRepository = new ListAndUserRepository(getApplicationContext());
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            wishlistArrayList = new ArrayList<>();
            wishlistArrayList_search = new ArrayList<>();
            listAndUserArrayList = new ArrayList<>();
            WishlistNumArrayList = new ArrayList<>();

            String username = getUsername();
            friendList = friendRepository.getAllFriend(username);

            ///get friend Lists
            for(Friend friend : friendList)
            {
                List<ListAndUser> foo = listAndUserRepository.getIDUser(friend.getId_ami());
                for(ListAndUser listAndUser : foo)
                {
                    WishlistNumArrayList.add(listAndUser.getNum_list());
                }
            }

            ///get user List
            List<ListAndUser> foo = listAndUserRepository.getIDUser(username);
            for(ListAndUser listAndUser : foo)
            {
                WishlistNumArrayList.add(listAndUser.getNum_list());
            }


            /// convert Num_List into wishlists
            for(int num : WishlistNumArrayList)
            {
                List<Wishlist> wishlistList = wishlistRepository.getByID(num);
                for(Wishlist wishlist : wishlistList)
                {
                    wishlistArrayList.add(wishlist);
                    wishlistArrayList_search.add(wishlist);
                }

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

    public String getUsername(){
        // Retrieving the value using its keys
        // the file name must be same in both saving
        // and retrieving the data
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);

        // The value will be default as empty string
        // because for the very first time
        // when the app is opened,
        // there is nothing to show
        return sh.getString("ID", "");
    }
}
