package com.example.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class WishlistsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlists);
    }

    @Override  //required for back animation
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
