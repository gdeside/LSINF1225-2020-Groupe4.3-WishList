package com.example.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateAccountActivity extends AppCompatActivity {

    private View.OnClickListener CreateAccount_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openLoginActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Button CreateAccount_btn = findViewById(R.id.CreateAccount_btn);
        CreateAccount_btn.setOnClickListener(CreateAccount_listener);
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
