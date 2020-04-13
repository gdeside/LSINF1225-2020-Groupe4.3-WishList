package com.example.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity {

    private View.OnClickListener CreateAccount_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(edt_id.getText().toString().isEmpty() ||
                edt_name.getText().toString().isEmpty() ||
                edt_surname.getText().toString().isEmpty() ||
                edt_password.getText().toString().isEmpty() ) {

                Toast.makeText(getApplicationContext(),"Please fills Details",Toast.LENGTH_LONG).show();

            } else {
                openLoginActivity();
            }
        }
    };

    EditText edt_id, edt_name, edt_surname, edt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Button CreateAccount_btn = findViewById(R.id.CreateAccount_btn);
        CreateAccount_btn.setOnClickListener(CreateAccount_listener);

        edt_id = (EditText) findViewById(R.id.edt_create_id);
        edt_name = (EditText) findViewById(R.id.edt_create_name);
        edt_surname = (EditText) findViewById(R.id.edt_create_surname);
        edt_password = (EditText) findViewById(R.id.edt_create_password);


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
