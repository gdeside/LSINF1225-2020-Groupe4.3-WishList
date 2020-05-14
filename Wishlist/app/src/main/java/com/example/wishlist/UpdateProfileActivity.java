package com.example.wishlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wishlist.Classesapp.Friend;
import com.example.wishlist.Classesapp.FriendRepository;
import com.example.wishlist.Classesapp.ListAndProductRepository;
import com.example.wishlist.Classesapp.ListAndUser;
import com.example.wishlist.Classesapp.ListAndUserRepository;
import com.example.wishlist.Classesapp.User;
import com.example.wishlist.Classesapp.UserRepository;
import com.example.wishlist.Classesapp.Wishlist;
import com.example.wishlist.Classesapp.WishlistRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class UpdateProfileActivity extends AppCompatActivity {

    private View.OnClickListener UpdateAccount_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UserRepository userRepository = new UserRepository(getApplicationContext());
            if (    up_name.getText().toString().isEmpty() ||
                    up_surname.getText().toString().isEmpty() ||
                    up_password.getText().toString().isEmpty() ) {

                Toast.makeText(getApplicationContext(),"Please fills Details",Toast.LENGTH_LONG).show();

            } else {


                New_password = up_password.getText().toString();
                New_name = up_name.getText().toString().trim();
                New_surname = up_surname.getText().toString().trim();
                description=up_description.getText().toString().trim();
                birth=up_date.getText().toString().trim();

                User user = new User(getUsername(),New_password,New_name,New_surname);
                user.setDescription(description);
                user.setDOB(birth);
                userRepository.UpdateTask(user);
                openProfileActivity();
            }
        }
    };

    EditText  up_name, up_surname, up_password,up_description,up_date;
    TextView profile_id;
    ImageView delete_profile;
    String  New_name, New_surname, New_password,description,birth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        Button UpdateAccount_btn = findViewById(R.id.btn_save_profil);
        UpdateAccount_btn.setOnClickListener(UpdateAccount_listener);

        up_name = (EditText) findViewById(R.id.edt_update_name);
        up_surname = (EditText) findViewById(R.id.edt_update_surname);
        up_password = (EditText) findViewById(R.id.edt_update_password);
        up_description=(EditText)findViewById(R.id.edt_update_description);
        delete_profile=(ImageView)findViewById(R.id.edit_deleteprofile);
        up_date=(EditText)findViewById(R.id.edt_update_DOB);

        profile_id= (TextView)findViewById(R.id.profile_identifiant);
        profile_id.setText(getUsername());

        delete_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                New_password = up_password.getText().toString();
                New_name = up_name.getText().toString().trim();
                New_surname = up_surname.getText().toString().trim();


                UserRepository userRepository = new UserRepository(getApplicationContext());
                User user = new User(getUsername(),New_password,New_name,New_surname);
                generate_delete_dialog(user);
            }
        });



    }

    @Override  //required for back animation
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


    public void generate_delete_dialog(User user)
    {
        final User user_delete = user;

        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateProfileActivity.this); //do not write getapplicationContextg
        builder.setTitle("WARNING");
        builder.setMessage("Are you sure to delete ?" + "\n Name : " + user_delete.getID() + "\n Description : " + user_delete.getDescription());
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delete_user(user_delete);
                openLoginActivity();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setIcon(android.R.drawable.ic_delete);


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public void openLoginActivity()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
    }

    public void delete_user(final User user) {
        UserRepository userRepository = new UserRepository(getApplicationContext());
        ListAndUserRepository listAndUserRepository = new ListAndUserRepository(getApplicationContext());
        FriendRepository friendRepository = new FriendRepository(getApplicationContext());

        Toast.makeText(getApplicationContext(),getUsername(),Toast.LENGTH_LONG).show();
        List<ListAndUser> listAndUserList = listAndUserRepository.getAllList(getUsername());
        for(ListAndUser listAndUser : listAndUserList)
        {
            int num_list = listAndUser.getNum_list();
            Wishlist foo = new Wishlist("A",true,"A");
            foo.setNum_list(num_list);
            final Wishlist wishlist = foo;

            delete_wishlist(wishlist);
        }

        List<Friend> friendList = friendRepository.getAllFriend(getUsername());
        for(Friend friend : friendList)
        {
            friendRepository.DeleteTask(friend);
        }

        userRepository.DeleteTask(user);
    }

    public void delete_wishlist(final Wishlist wishlist)
    {
        WishlistRepository wishlistRepository = new WishlistRepository(getApplicationContext());
        ListAndUserRepository listAndUserRepository = new ListAndUserRepository(getApplicationContext());
        ListAndProductRepository listAndProductRepository = new ListAndProductRepository(getApplicationContext());

        wishlistRepository.DeleteTask(wishlist);
        listAndUserRepository.DeleteWishlist(wishlist.getNum_list());
        listAndProductRepository.DeleteWishlist(wishlist.getNum_list());
    }

    public void openProfileActivity()
    {
        Intent intent = new Intent(this,ProfileActivity.class);
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
