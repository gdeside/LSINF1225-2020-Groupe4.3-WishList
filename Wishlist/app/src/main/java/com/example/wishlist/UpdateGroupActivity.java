package com.example.wishlist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wishlist.Classesapp.FriendRepository;
import com.example.wishlist.Classesapp.Grp;
import com.example.wishlist.Classesapp.GrpAndUser;
import com.example.wishlist.Classesapp.GrpAndUserRepository;
import com.example.wishlist.Classesapp.GrpRepository;
import com.example.wishlist.Classesapp.UserRepository;

public class UpdateGroupActivity extends AppCompatActivity {


    //à finir
    private View.OnClickListener UpdateGR_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            GrpRepository grpRepository=new GrpRepository(getApplicationContext());
            UserRepository userRepository=new UserRepository(getApplicationContext());
            if (group_name.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),"Please fill New Name",Toast.LENGTH_LONG).show();
            }
            else {
                Grp grp = new Grp(group_name.getText().toString().trim());
                grp.setID(group_id);
                grpRepository.UpdateTask(grp);
            }
        }
    };

    private View.OnClickListener AddFriend_listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UserRepository userRepository = new UserRepository(getApplicationContext());
            GrpAndUserRepository grpAndUserRepository = new GrpAndUserRepository(getApplicationContext());

            String user_name_query = search_friend.getQuery().toString().trim();

            if (user_name_query.isEmpty()){
                Toast.makeText(getApplicationContext(),"Please fill Friend Name",Toast.LENGTH_LONG).show();
            }else if (!userRepository.isIDUsed(user_name_query)){

                Toast.makeText(getApplicationContext(),"User doesnt exist",Toast.LENGTH_LONG).show();
            }
            else{

                GrpAndUser grpAndUser = new GrpAndUser(user_name_query,group_id);
                grpAndUserRepository.InsertTask(grpAndUser);
            }
        }
    };



    SearchView search_friend;
    EditText group_name;
    Button group_save,add_user;
    String new_groupename;
    int group_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_group);

        search_friend=findViewById(R.id.sw_update_group_friend);
        group_name=findViewById(R.id.edt_update_group_name);
        group_save=findViewById(R.id.btn_update_group_save);
        add_user=findViewById(R.id.btn_update_group_add);

        group_save.setOnClickListener(UpdateGR_listener);
        add_user.setOnClickListener(AddFriend_listener);

        ///------------------------- Set variables -------------------------------------------------
        Bundle data = getIntent().getExtras();
        if(data != null) {
            group_id = data.getInt("Group_ID");
        }
        ///-----------------------------------------------------------------------------------------
    }


}
