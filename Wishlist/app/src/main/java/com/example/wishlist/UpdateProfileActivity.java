package com.example.wishlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wishlist.Classesapp.ListAndUser;
import com.example.wishlist.Classesapp.ListAndUserRepository;
import com.example.wishlist.Classesapp.User;
import com.example.wishlist.Classesapp.UserRepository;

import java.util.List;


public class UpdateProfileActivity extends AppCompatActivity {

    private View.OnClickListener UpdateAccount_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UserRepository userRepository = new UserRepository(getApplicationContext());
            if ( up_id.getText().toString().isEmpty() ||
                    up_name.getText().toString().isEmpty() ||
                    up_surname.getText().toString().isEmpty() ||
                    up_password.getText().toString().isEmpty() ) {

                Toast.makeText(getApplicationContext(),"Please fills Details",Toast.LENGTH_LONG).show();

            } else {


                New_id = up_id.getText().toString().trim();
                New_password = up_password.getText().toString();
                New_name = up_name.getText().toString().trim();
                New_surname = up_surname.getText().toString().trim();

                User user = new User(New_id,New_password,New_name,New_surname);
                userRepository.InsertTask(user);
            }
        }
    };

    EditText up_id, up_name, up_surname, up_password,up_date;
    ImageView delete_profile;
    String New_id, New_name, New_surname, New_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        Button UpdateAccount_btn = findViewById(R.id.save_profil);
        UpdateAccount_btn.setOnClickListener(UpdateAccount_listener);

        up_id = (EditText) findViewById(R.id.edt_update_ID_login);
        up_name = (EditText) findViewById(R.id.edt_update_name);
        up_surname = (EditText) findViewById(R.id.edt_update_surname);
        up_password = (EditText) findViewById(R.id.edt_update_password);
        delete_profile=(ImageView)findViewById(R.id.edit_deleteprofile);

        delete_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                New_id = up_id.getText().toString().trim();
                New_password = up_password.getText().toString();
                New_name = up_name.getText().toString().trim();
                New_surname = up_surname.getText().toString().trim();

                UserRepository userRepository = new UserRepository(getApplicationContext());
                User user = new User(New_id,New_password,New_name,New_surname);
                user.setID(getUsername());
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
        userRepository.DeleteTask(user);
        List<ListAndUser> listAndUserList = listAndUserRepository.getIDUser(u)
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
