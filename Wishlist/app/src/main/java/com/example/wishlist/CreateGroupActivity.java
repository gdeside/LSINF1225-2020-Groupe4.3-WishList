package com.example.wishlist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wishlist.Classesapp.Grp;
import com.example.wishlist.Classesapp.GrpAndUser;
import com.example.wishlist.Classesapp.GrpAndUserRepository;
import com.example.wishlist.Classesapp.GrpRepository;

public class CreateGroupActivity extends AppCompatActivity {

    private View.OnClickListener CreatGRP_listener=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            GrpRepository grpRepository = new GrpRepository(getApplicationContext());
            GrpAndUserRepository grpAndUserRepository = new GrpAndUserRepository(getApplicationContext());
            namegrp=name_group.getText().toString().trim();
            String username = getUsername();

            if (name_group.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),"Please fills Details", Toast.LENGTH_LONG).show();
            }
            else {

                Grp grp1 = new Grp(namegrp);
                int id_group = Math.toIntExact(grpRepository.InsertTask(grp1));

                GrpAndUser grpAndUser = new GrpAndUser(username,id_group);
                grpAndUserRepository.InsertTask(grpAndUser);


                String searcher=searchfriend.getQuery().toString();
                openViewGroupsActivity();
            }
        }
    };

    Button btn_creatgr;
    EditText name_group;
    SearchView searchfriend;

    String namegrp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        btn_creatgr=findViewById(R.id.btn_creategr);
        name_group=findViewById(R.id.creategr_name);
        searchfriend=findViewById(R.id.find_friends_grp);


        btn_creatgr.setOnClickListener(CreatGRP_listener);
    }

    public void openViewGroupsActivity()
    {
        Intent intent = new Intent(this, ViewGroupsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
    }

    /// Give logged user ID
    public String getUsername(){
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
        return sh.getString("ID", "");
    }
}
