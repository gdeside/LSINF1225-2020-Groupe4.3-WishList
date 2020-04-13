package com.example.wishlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.wishlist.Classesapp.UserRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {


    private View.OnClickListener Profile_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openProfileActivity();
        }
    };

    private View.OnClickListener Wishlists_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openWishlistsActivity();
        }
    };

    private View.OnClickListener Friendlist_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openFriendlistActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button wishlists_btn = findViewById(R.id.wishlists_btn);
        Button profile_btn = findViewById(R.id.profile_btn);
        Button friendlist_btn = findViewById(R.id.friendlist_btn);

        wishlists_btn.setOnClickListener(Wishlists_listener);
        profile_btn.setOnClickListener(Profile_listener);
        friendlist_btn.setOnClickListener(Friendlist_listener);

        //Initalize and Assign Bottom Navigation View
        BottomNavigationView navigationView = findViewById(R.id.bottomNavigationView);

        //Set Home Button Selected
        navigationView.setSelectedItemId(R.id.bottomNavHome_btn);

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
                        //startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
                        return true;
                    case R.id.bottomNavWishlists_btn :
                        startActivity(new Intent(getApplicationContext(),WishlistsActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
                        return true;
                }
                return false;
            }
        });

        UserRepository userRepository = new UserRepository(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.logout_btn:
            openLoginActivity();
            return(true);

        case android.R.id.home :
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            return true;
    }
        return(super.onOptionsItemSelected(item));
    }



    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


    public void openLoginActivity()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);// clear la Back stack
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
    }

    public void openProfileActivity()
    {
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
    }

    public void openFriendlistActivity()
    {
        Intent intent = new Intent(this, FriendlistActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
    }

    public void openWishlistsActivity()
    {
        Intent intent = new Intent(this, WishlistsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
    }


}
