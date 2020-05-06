package com.example.wishlist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wishlist.Classesapp.User;
import com.example.wishlist.Classesapp.UserRepository;
import com.example.wishlist.Classesapp.Wishlist;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    ImageButton edit_profil;
    TextView ID_tv, name_tv, surname_tv, DOB_tv, Description_tv;

    private View.OnClickListener Edit_profil_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openUpdateActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ///--------------------------- Set Variables -----------------------------------------------
        edit_profil=findViewById(R.id.edit_profil);
        edit_profil.setOnClickListener(Edit_profil_listener);

        ID_tv = findViewById(R.id.Profile_ID_tv);
        name_tv = findViewById(R.id.Profile_Name_tv);
        surname_tv = findViewById(R.id.Profile_Surname_tv);
        DOB_tv = findViewById(R.id.Profile_DOB_tv);
        Description_tv = findViewById(R.id.Profile_Description_tv);
        ///-----------------------------------------------------------------------------------------

        /// Set Profile
        String username = getUsername();
        setProfile(username);

        //------------------------------ Bottom Navigation View ------------------------------------

        //Initalize and Assign Bottom Navigation View
        BottomNavigationView navigationView = findViewById(R.id.bottomNavigationView);

        //Set Profile Button Selected
        navigationView.setSelectedItemId(R.id.bottomNavProfile_btn);

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
                        //startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
                        return true;
                    case R.id.bottomNavHome_btn :
                        startActivity(new Intent(getApplicationContext(), WishlistsActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
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

    public void setProfile(String Username)
    {
        UserRepository userRepository = new UserRepository(getApplicationContext());
        User user = userRepository.getUserByID(Username);

        name_tv.setText(user.getName());
        surname_tv.setText(user.getSurname());
        DOB_tv.setText(user.getDOB());
        Description_tv.setText(user.getDescription());
        ID_tv.setText(Username);
    }

    @Override  //required for back animation
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override //Animation requierement
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {

        case android.R.id.home :
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            return true;
    }
        return(super.onOptionsItemSelected(item));
    }


    public void openUpdateActivity()
    {
        Intent intent = new Intent(this, UpdateProfileActivity.class);
        startActivity(intent);

    }

    /// Give logged user ID
    public String getUsername(){
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
        return sh.getString("ID", "");
    }

}
