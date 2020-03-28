package com.example.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);

        Button wishlists_btn = findViewById(R.id.wishlists_btn);
        Button profile_btn = findViewById(R.id.profile_btn);
        Button friendlist_btn = findViewById(R.id.friendlist_btn);

        wishlists_btn.setOnClickListener(Wishlists_listener);
        profile_btn.setOnClickListener(Profile_listener);
        friendlist_btn.setOnClickListener(Friendlist_listener);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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
