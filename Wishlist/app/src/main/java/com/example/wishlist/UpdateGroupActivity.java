package com.example.wishlist;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateGroupActivity extends AppCompatActivity {

    SearchView searcch_friend;
    EditText up_group_name;
    Button up_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_group);

        searcch_friend=findViewById(R.id.up_group_seeach);
        up_group_name=findViewById(R.id.up_namegroup);
        up_save=findViewById(R.id.up_savegroup);

    }
}
