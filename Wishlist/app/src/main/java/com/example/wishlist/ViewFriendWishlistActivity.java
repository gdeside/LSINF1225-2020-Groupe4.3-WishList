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

public class ViewFriendWishlistActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    WishlistAdapter wishlistAdapter;
    ArrayList<Wishlist> wishlistArrayList, wishlistArrayList_search;
    EditText edt_wishlist_search;

    String Friend_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_friend_wishlist);

        Bundle data = getIntent().getExtras();
        if(data != null)
        {
            Friend_ID = data.getString("ID_Friend");
        }

        ////----------------------- Card layout management -----------------------------------------
        recyclerView = (RecyclerView)findViewById(R.id.friend_Wishlist_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ///-----------------------------------------------------------------------------------------

        ///-------------------------------- Search bar ---------------------------------------------
        edt_wishlist_search = findViewById(R.id.edt_friend_wishlist_search);
        edt_wishlist_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text=s.toString();
                Filter(text);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        ///-----------------------------------------------------------------------------------------

        /// Load Data
        new ViewFriendWishlistActivity.LoadDataTask().execute();
    }

    /// Load Data
    class LoadDataTask extends AsyncTask<Void,Void,Void>
    {
        WishlistRepository wishlistRepository;
        FriendRepository friendRepository;
        ListAndUserRepository listAndUserRepository;
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
            String username = Friend_ID;

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
                    if(wishlist.getOption())
                    {
                        wishlistArrayList.add(wishlist);
                        wishlistArrayList_search.add(wishlist);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            wishlistAdapter = new WishlistAdapter(wishlistArrayList, ViewFriendWishlistActivity.this);
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
                if(wishlist.getName().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    wishlistArrayList.add(wishlist);
                }
            }
            wishlistAdapter.notifyDataSetChanged();
        }
    }

    @Override  //required for back animation
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    /// If you go back to this activity, reload the data
    @Override
    protected void onRestart(){
        super.onRestart();
        new ViewFriendWishlistActivity.LoadDataTask().execute();
    }

    /// Give logged user ID
    public String getUsername(){
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
        return sh.getString("ID", "");
    }
}
