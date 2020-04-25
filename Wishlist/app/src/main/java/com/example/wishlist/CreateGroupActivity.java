package com.example.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CreateGroupActivity extends AppCompatActivity {

    Button btn_creatgr;
    EditText name_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);


        btn_creatgr=findViewById(R.id.btn_creategr);
        name_group=findViewById(R.id.creategr_name);
    }
}
