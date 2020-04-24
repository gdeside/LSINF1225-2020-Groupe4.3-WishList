package com.example.wishlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FriendProfileActivity extends AppCompatActivity {

    TextView friendUsername, friendName, friendBirthday;
    Button btnWishlistFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);

        friendUsername = findViewById(R.id.friendUsername);
        friendName = findViewById(R.id.friendName);
        friendBirthday = findViewById(R.id.friendBirthday);
        btnWishlistFriend = findViewById(R.id.btnWishlistFriend);
    }

    @Override  //required for back animation
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
