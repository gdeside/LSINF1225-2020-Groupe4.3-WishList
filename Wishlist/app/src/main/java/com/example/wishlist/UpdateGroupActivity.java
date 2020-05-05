package com.example.wishlist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wishlist.Classesapp.GrpRepository;
import com.example.wishlist.Classesapp.UserRepository;

public class UpdateGroupActivity extends AppCompatActivity {


    //à finir
    private View.OnClickListener UpdateGR_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            GrpRepository grpRepository=new GrpRepository(getApplicationContext());
            UserRepository userRepository=new UserRepository(getApplicationContext());
            if (up_group_name.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),"Please fills Details",Toast.LENGTH_LONG).show();
            }else {
                String SearchQuerry = searcch_friend.getQuery().toString();
                if(!userRepository.isIDUsed(SearchQuerry.trim()))
                {

                    Toast.makeText(getApplicationContext(),"User doesn't exist",Toast.LENGTH_LONG).show();
                }else{
                    new_groupename=up_group_name.getText().toString().trim();
                    //grpRepository.UpdateTask(grp);

                }


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
