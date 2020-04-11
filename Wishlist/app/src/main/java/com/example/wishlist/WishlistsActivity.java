package com.example.wishlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WishlistsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlists);

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
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
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
}