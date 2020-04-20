package com.example.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class create_friendship extends AppCompatActivity {
    Button btn_friendship;
    EditText friendship_nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_friendship);


        //éléments layout
        btn_friendship=findViewById(R.id.createfd_btn);
        friendship_nickname=findViewById(R.id.createfd_nickname);
    }
}
