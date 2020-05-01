package com.example.wishlist;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateFriendship extends AppCompatActivity {


    EditText up_nickname;
    ImageButton deletebutton;
    Button saveupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_friendship);

        up_nickname=findViewById(R.id.up_friend_nickname);
        deletebutton=findViewById(R.id.up_friend_delete);
        saveupdate=findViewById(R.id.up_friend_save);
    }

}
