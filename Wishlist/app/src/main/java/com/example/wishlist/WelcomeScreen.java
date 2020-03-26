package com.example.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity {
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

       login=findViewById(R.id.login);
       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openloginActivity();
           }
       });
    }
    public void openloginActivity(){
        Intent intent =new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
