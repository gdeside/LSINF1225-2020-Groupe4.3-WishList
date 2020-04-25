package com.example.wishlist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateFriendshipActivity extends AppCompatActivity {

    SearchView searchfriend;
    Button btn_friendship;
    EditText friendship_nickname;

    private View.OnClickListener create_friendship_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(),"Please fills Details",Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_friendship);


        //éléments layout
        btn_friendship=findViewById(R.id.createfd_btn);
        friendship_nickname=findViewById(R.id.createfd_nickname);
        searchfriend=findViewById(R.id.search_friend);
        btn_friendship.setOnClickListener(create_friendship_listener);

    }
}
