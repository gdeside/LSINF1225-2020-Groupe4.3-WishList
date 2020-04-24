package com.example.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewWishlistProductActivity extends AppCompatActivity {

    String name, description;
    int wishlist_num;
    Boolean option;

    private View.OnClickListener SettingsWishlist_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openUpdateWishlistsActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_wishlist_product);

        Button SettingsWishlist_btn = findViewById(R.id.SettingsWishlist_btn);
        SettingsWishlist_btn.setOnClickListener(SettingsWishlist_listener);

        ///Get values
        Bundle data = getIntent().getExtras();
        if(data != null)
        {
            name = data.getString("wishlist_name");
            description = data.getString("wishlist_description");
            option = data.getBoolean("wishlist_option");
            wishlist_num = data.getInt("wishlist_num");
        }

    }
    public void openUpdateWishlistsActivity()
    {
        Intent intent = new Intent(this, UpdateWishlistActivity.class);
        intent.putExtra("wishlist_name",name);
        intent.putExtra("wishlist_description",description);
        intent.putExtra("wishlist_option",option);
        intent.putExtra("wishlist_num",wishlist_num);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
    }

}
