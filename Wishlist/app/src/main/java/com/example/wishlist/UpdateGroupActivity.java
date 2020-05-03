package com.example.wishlist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateGroupActivity extends AppCompatActivity {


    //à finir
    private View.OnClickListener UpdateFriendship_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (up_group_name.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),"Please fills Details",Toast.LENGTH_LONG).show();
            }else {
                new_groupename=up_group_name.getText().toString().trim();
            }
        }
    };




    SearchView searcch_friend;
    EditText up_group_name;
    Button up_save;
    String new_groupename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_group);

        searcch_friend=findViewById(R.id.up_group_seeach);
        up_group_name=findViewById(R.id.up_namegroup);
        up_save=findViewById(R.id.up_savegroup);
    }


}
