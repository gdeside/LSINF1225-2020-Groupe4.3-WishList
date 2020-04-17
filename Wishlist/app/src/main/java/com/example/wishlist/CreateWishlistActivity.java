package com.example.wishlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.wishlist.Classesapp.Wishlist;
import com.example.wishlist.Classesapp.WishlistRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CreateWishlistActivity extends AppCompatActivity {



    EditText edt_wishlist_name;
    RadioButton public_btn, private_btn;


    String name;
    Boolean option;


    private View.OnClickListener CreateWishlist_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(edt_wishlist_name.getText().toString().isEmpty() ) {

                Toast.makeText(getApplicationContext(),"Please fill name",Toast.LENGTH_LONG).show();

            } ///Create Wishlist
            else {
                name = edt_wishlist_name.getText().toString().trim();
                option = public_btn.isChecked();

                WishlistRepository wishlistRepository = new WishlistRepository(getApplicationContext());
                Wishlist wishlist = new Wishlist(name,option,getUsername(),"");
                wishlistRepository.InsertTask(wishlist);

                edt_wishlist_name.setText("");
                openHomeActivity();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_wishlist);

        Button CreateWishlist_btn = findViewById(R.id.CreateWishlist_btn);
        CreateWishlist_btn.setOnClickListener(CreateWishlist_listener);

        edt_wishlist_name = (EditText) findViewById(R.id.edt_wishlist_name);
        public_btn = findViewById(R.id.rbtn_public);
        private_btn = findViewById(R.id.rbtn_private);


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


    }

    @Override  //required for back animation
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override ///I'm not sure what it does but i'm too afraid to delete it
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {

        case android.R.id.home :
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            return true;
    }
        return(super.onOptionsItemSelected(item));
    }

    public void openHomeActivity()
    {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
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
