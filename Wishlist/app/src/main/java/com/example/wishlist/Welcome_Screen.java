package com.example.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome_Screen extends AppCompatActivity {

    private View.OnClickListener login_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openLoginActivity();
        }
    };


    private View.OnClickListener createaccount_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            opencreateaccountActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome__screen);

        //button login
        Button loginbutton=findViewById(R.id.login_button);
        loginbutton.setOnClickListener(login_listener);

        //button sign up
        Button signupbutton=findViewById(R.id.signup_button);
        signupbutton.setOnClickListener(createaccount_listener);

    }


    void openLoginActivity(){
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
    void opencreateaccountActivity(){
        Intent intent=new Intent(this,CreateAccountActivity.class);
        startActivity(intent);
    }
}
