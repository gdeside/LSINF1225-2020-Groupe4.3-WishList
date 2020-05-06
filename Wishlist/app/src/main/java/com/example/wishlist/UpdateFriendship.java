package com.example.wishlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wishlist.Classesapp.Friend;
import com.example.wishlist.Classesapp.FriendRepository;
import com.example.wishlist.Classesapp.Wishlist;

import java.util.List;

public class UpdateFriendship extends AppCompatActivity {

    FriendRepository friendRepository;
    EditText up_nickname;
    Button saveupdate;
    ImageButton delete_friend;
    String new_nickname,Friend_ID;

    private View.OnClickListener UpdateFriendship_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FriendRepository friendRepository=new FriendRepository(getApplicationContext());
            if (up_nickname.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),"Please fills Details",Toast.LENGTH_LONG).show();
            }else {
                new_nickname=up_nickname.getText().toString().trim();
                Friend friend = new Friend(Friend_ID,getUsername(),new_nickname);
                friendRepository.UpdateTask(friend);
                openFriendProfileActivity(Friend_ID);
            }
        }
    };

    private View.OnClickListener DeleteFriendship_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Friend friend = friendRepository.getFriendByID(Friend_ID,getUsername());
            generate_delete_dialog(friend);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_friendship);

        ///--------------------------- Set Variables -----------------------------------------------
        friendRepository = new FriendRepository(getApplicationContext());
        up_nickname=findViewById(R.id.up_friend_nickname);
        saveupdate=findViewById(R.id.up_friend_save);
        delete_friend = findViewById(R.id.up_friend_delete);

        Bundle data = getIntent().getExtras();
        if(data != null)
        {
            Friend_ID = data.getString("ID_Friend");
        }
        ///-----------------------------------------------------------------------------------------

        saveupdate.setOnClickListener(UpdateFriendship_listener);
        delete_friend.setOnClickListener(DeleteFriendship_listener);

    }

    public void openFriendProfileActivity(String ID_Friend){
        Intent intent=new Intent(this,FriendProfileActivity.class);
        intent.putExtra("ID_Friend",ID_Friend);
        startActivity(intent);
    }

    public void generate_delete_dialog(Friend friend)
    {
        final Friend friend_delete = friend;

        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateFriendship.this); //do not write getapplicationContextg
        builder.setTitle("WARNING");
        builder.setMessage("Are you sure to delete ?" + "\n Userame : " + friend_delete.getId_ami() + "\n Nickname : " + friend_delete.getSurname());

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FriendRepository friendRepository = new FriendRepository(getApplicationContext());
                friendRepository.DeleteTask(friend_delete);
                openFriendlistActivity();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });

        builder.setIcon(android.R.drawable.ic_delete);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /// Give logged user ID
    public String getUsername(){
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
        return sh.getString("ID", "");
    }


    public void openFriendlistActivity()
    {
        Intent intent = new Intent(this, FriendlistActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
    }

}
