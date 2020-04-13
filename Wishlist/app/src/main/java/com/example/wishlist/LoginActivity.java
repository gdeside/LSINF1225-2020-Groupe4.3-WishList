package com.example.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    private View.OnClickListener Login_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           openMainActivity();
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
