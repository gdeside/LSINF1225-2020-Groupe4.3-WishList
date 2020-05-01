package com.example.wishlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.wishlist.Classesapp.Friend;
import com.example.wishlist.Classesapp.FriendRepository;
import com.example.wishlist.Classesapp.ListAndUser;
import com.example.wishlist.Classesapp.ListAndUserRepository;
import com.example.wishlist.Classesapp.Wishlist;
import com.example.wishlist.Classesapp.WishlistRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WishlistsActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    WishlistAdapter wishlistAdapter;
    ArrayList<Wishlist> wishlistArrayList, wishlistArrayList_search;
    EditText edt_wishlist_search;

    private View.OnClickListener CreateWishlist_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openCreateWishlistsActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlists);

        FloatingActionButton createWishlists_btn = findViewById(R.id.floatingWishlist);

        createWishlists_btn.setOnClickListener(CreateWishlist_listener);


        //Initalize and Assign Bottom Navigation View
        BottomNavigationView navigationView = findViewById(R.id.bottomNavigationView);

        //Set Wishlists Button Selected
        navigationView.setSelectedItemId(R.id.bottomNavWishlists_btn);

        ///Perform ItemSelected
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.bottomNavFriendlist_btn:
                        startActivity(new Intent(getApplicationContext(),FriendlistActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
                        return true;
                    case R.id.bottomNavProfile_btn:
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
                        return true;
                    case R.id.bottomNavHome_btn :
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
                        return true;
                    case R.id.bottomNavWishlists_btn :
                        // startActivity(new Intent(getApplicationContext(),WishlistsActivity.class));
                        // overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
                        return true;
                }
                return false;
            }
        });

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
        new WishlistsActivity.LoadDataTask().execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {

        case android.R.id.home :
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            return true;
    }
        return(super.onOptionsItemSelected(item));
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

            wishlistAdapter = new WishlistAdapter(wishlistArrayList, WishlistsActivity.this);
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

    @Override  //required for back animation
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        new WishlistsActivity.LoadDataTask().execute();
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


    public void openCreateWishlistsActivity()
    {
        Intent intent = new Intent(this, CreateWishlistActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
    }
}
