package com.example.wishlist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wishlist.Classesapp.Friend;
import com.example.wishlist.Classesapp.FriendRepository;
import com.example.wishlist.Classesapp.ListAndUser;
import com.example.wishlist.Classesapp.ListAndUserRepository;
import com.example.wishlist.Classesapp.Product;
import com.example.wishlist.Classesapp.ProductRepository;
import com.example.wishlist.Classesapp.Wishlist;
import com.example.wishlist.Classesapp.WishlistRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FriendlistActivity extends AppCompatActivity {


    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    FriendAdapter friendAdapter;
    ArrayList<Friend> friendArrayList;
    FloatingActionButton add_friend;


    private View.OnClickListener add_friend_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openCreateFrienship();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlist);

        add_friend = findViewById(R.id.btn_add_friend);
        add_friend.setOnClickListener(add_friend_listener);

        ///To see cards
        recyclerView = (RecyclerView) findViewById(R.id.Friendlist_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        new LoadDataTask().execute();

        //Initalize and Assign Bottom Navigation View
        BottomNavigationView navigationView = findViewById(R.id.bottomNavigationView);

        //Set Friendlist Button Selected
        navigationView.setSelectedItemId(R.id.bottomNavFriendlist_btn);

        ///Perform ItemSelected
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.bottomNavFriendlist_btn:
                        //startActivity(new Intent(getApplicationContext(),FriendlistActivity.class));
                        //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
                        return true;
                    case R.id.bottomNavProfile_btn:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
                        return true;
                    case R.id.bottomNavHome_btn:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
                        return true;
                    case R.id.bottomNavWishlists_btn:
                        startActivity(new Intent(getApplicationContext(), WishlistsActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
                        return true;
                }
                return false;
            }
        });
    }

    class LoadDataTask extends AsyncTask<Void,Void,Void>
    {
        FriendRepository friendRepository ;
        List<Friend> friendList;
        String username;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            friendRepository= new FriendRepository(getApplicationContext());
            username = getUsername();
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            friendList = friendRepository.getAllFriend_unsync(username);
            friendArrayList = new ArrayList<>();

            for(int i =0; i <friendList.size();i++)
            {
               friendArrayList.add(friendList.get(i));
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);

            FriendAdapter friendAdapter = new FriendAdapter(friendArrayList,FriendlistActivity.this, getApplicationContext());
            recyclerView.setAdapter(friendAdapter);
        }
    }






    @Override  //required for back animation
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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

    public void Reload()
    {
        new FriendlistActivity.LoadDataTask().execute();
    }

    public void openCreateFrienship(){
        Intent intent=new Intent(this, CreateFriendshipActivity.class);
        startActivity(intent);
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
