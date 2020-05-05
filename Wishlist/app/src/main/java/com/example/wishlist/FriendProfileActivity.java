package com.example.wishlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wishlist.Classesapp.Friend;
import com.example.wishlist.Classesapp.FriendRepository;
import com.example.wishlist.Classesapp.User;
import com.example.wishlist.Classesapp.UserRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FriendProfileActivity extends AppCompatActivity {

    TextView friendUsername, friendName, friendBirthday;
    Button btnWishlistFriend;
    ImageButton edit_surname;

    String Friend_ID;


    private View.OnClickListener FriendSurname_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openUpdateFriendshipActivity(Friend_ID);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);

        friendUsername = findViewById(R.id.tv_friend_username);
        friendName = findViewById(R.id.tv_friend_name);
        friendBirthday = findViewById(R.id.tv_friend_DOB);
        btnWishlistFriend = findViewById(R.id.btn_friend_wishlist);
        edit_surname = findViewById(R.id.btn_edit_profile_surname);

        edit_surname.setOnClickListener(FriendSurname_listener);

        Bundle data = getIntent().getExtras();
        if(data != null)
        {
            Friend_ID = data.getString("ID_Friend");
        }
        setProfile(Friend_ID);


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
                        //startActivity(new Intent(getApplicationContext(),FriendlistActivity.class));
                        //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
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
                        startActivity(new Intent(getApplicationContext(),WishlistsActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
                        return true;
                }
                return false;
            }
        });

    }

    public void setProfile(String Username)
    {
        UserRepository userRepository = new UserRepository(getApplicationContext());
        User user = userRepository.getUserByID(Username);

        friendName.setText(user.getName());
        friendUsername.setText(user.getSurname());
        friendBirthday.setText(user.getDOB());
        ///.setText(user.getDescription());
        friendUsername.setText(Username);
    }

    public void openUpdateFriendshipActivity(String ID_Friend){
        Intent intent=new Intent(this,UpdateFriendship.class);
        intent.putExtra("ID_Friend",ID_Friend);
        startActivity(intent);
    }

    @Override  //required for back animation
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
