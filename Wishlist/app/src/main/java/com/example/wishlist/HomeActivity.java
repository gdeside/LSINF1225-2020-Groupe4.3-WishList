package com.example.wishlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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


    private View.OnClickListener CreateProduct_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openCreateProductActivity();
        }
    };

    private View.OnClickListener ViewProduct_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openViewProductActivity();
        }
    };
    private View.OnClickListener UpdateFriend_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openUpdateFriendship();
        }
    };
    private View.OnClickListener FriendProfile_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openFriendProfile();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ///---------------------------- Set Variables-----------------------------------------------
        Button wishlists_btn = findViewById(R.id.wishlists_btn);
        Button profile_btn = findViewById(R.id.profile_btn);
        Button friendlist_btn = findViewById(R.id.friendlist_btn);
        Button create_product_btn = findViewById(R.id.Create_Product_btn);
        Button view_product_btn = findViewById(R.id.View_Product_btn);
        Button updatefrien_btn=findViewById(R.id.btn_updatefriend);
        Button friendprofil_btn = findViewById(R.id.friendProfile_btn);

        wishlists_btn.setOnClickListener(Wishlists_listener);
        profile_btn.setOnClickListener(Profile_listener);
        friendlist_btn.setOnClickListener(Friendlist_listener);
        create_product_btn.setOnClickListener(CreateProduct_listener);
        view_product_btn.setOnClickListener(ViewProduct_listener);
        updatefrien_btn.setOnClickListener(UpdateFriend_listener);
        friendprofil_btn.setOnClickListener(FriendProfile_listener);
        ///-----------------------------------------------------------------------------------------


        ///------------------------------- Bottom Navigation View ----------------------------------

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
                    case R.id.bottomNavProducts_btn :
                        startActivity(new Intent(getApplicationContext(),ViewProductActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
                        return true;
                }
                return false;
            }
        });
        ///-----------------------------------------------------------------------------------------
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



    public void openCreateProductActivity()
    {
        Intent intent = new Intent(this, CreateProductActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
    }

    public void openViewProductActivity()
    {
        Intent intent = new Intent(this, ViewProductActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
    }
    public void openUpdateFriendship(){
        Intent intent=new Intent(this,UpdateFriendship.class);
        startActivity(intent);
    }
    public void openFriendProfile(){
        Intent intent = new Intent(this, FriendProfileActivity.class);
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
