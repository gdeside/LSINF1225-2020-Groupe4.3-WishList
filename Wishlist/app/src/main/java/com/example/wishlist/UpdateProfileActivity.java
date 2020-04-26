package com.example.wishlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wishlist.Classesapp.User;
import com.example.wishlist.Classesapp.UserRepository;

import java.util.Date;

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
    String New_id, New_name, New_surname, New_password;
    Date dateofbith;

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
        up_date=(EditText) findViewById(R.id.edt_update_DateofBirth);



    }

    @Override  //required for back animation
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void openLoginActivity()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
    }
}
