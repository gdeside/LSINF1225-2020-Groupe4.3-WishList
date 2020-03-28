package com.example.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Button loginbutton=findViewById(R.id.log_in_button);
        Button createabutton=findViewById(R.id.createa_button);
    }
}
