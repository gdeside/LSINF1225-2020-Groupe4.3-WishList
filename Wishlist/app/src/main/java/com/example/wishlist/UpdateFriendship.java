package com.example.wishlist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wishlist.Classesapp.FriendRepository;

public class UpdateFriendship extends AppCompatActivity {



    //à finir
    private View.OnClickListener UpdateFriendship_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FriendRepository friendRepository=new FriendRepository(getApplicationContext());
            if (up_nickname.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),"Please fills Details",Toast.LENGTH_LONG).show();
            }else {
                new_nickname=up_nickname.getText().toString().trim();
            }
        }
    };


    EditText up_nickname;
    ImageButton deletebutton;
    Button saveupdate;


    String new_nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_friendship);

        up_nickname=findViewById(R.id.up_friend_nickname);
        deletebutton=findViewById(R.id.up_friend_delete);
        saveupdate=findViewById(R.id.up_friend_save);


        saveupdate.setOnClickListener(UpdateFriendship_listener);
    }

}
