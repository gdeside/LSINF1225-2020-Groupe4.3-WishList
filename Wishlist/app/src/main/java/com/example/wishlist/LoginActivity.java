package com.example.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wishlist.Classesapp.UserRepository;

public class LoginActivity extends AppCompatActivity {

    EditText edt_ID_login, edt_password_login;
    String S_id, S_password;

    private View.OnClickListener Login_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            UserRepository userRepository = new UserRepository(getApplicationContext());

            if(edt_ID_login.getText().toString().isEmpty() ||
                    edt_password_login.getText().toString().isEmpty())
            {
                Toast.makeText(getApplicationContext(),"Please fills Details",Toast.LENGTH_LONG).show();
            }
            else if(userRepository.CheckLogin(edt_ID_login.getText().toString(), edt_password_login.getText().toString()))
            {
                Toast.makeText(getApplicationContext(),"No User with those credentials",Toast.LENGTH_LONG).show();
            }
            else
            {
                // Storing data into SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

                // Creating an Editor object
                // to edit(write to the file)
                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                // Storing the key and its value
                // as the data fetched from edittext
                myEdit.putString("ID", edt_ID_login.getText().toString());


                // Once the changes have been made,
                // we need to commit to apply those changes made,
                // otherwise, it will throw an error
                myEdit.commit();

                openMainActivity();
            }
        }
    };

    private View.OnClickListener NewAccount_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openCreateAccountActivity();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(Login_listener);
        edt_ID_login = findViewById(R.id.edt_ID_login);
        edt_password_login = findViewById(R.id.edt_password_login);

        Button NewAccount_btn = findViewById(R.id.NewAccount_btn);
        NewAccount_btn.setOnClickListener(NewAccount_listener);
    }

    @Override  //required for back animation
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void openMainActivity()
    {
        Intent intent = new Intent(this, HomeActivity.class);

        // Clear back stack : Empeche l'utilisateur de revenir à l'écran de login sans se logout
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
    }

    public void openCreateAccountActivity()
    {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
    }
}
