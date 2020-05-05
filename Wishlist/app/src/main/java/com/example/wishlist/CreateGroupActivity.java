package com.example.wishlist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wishlist.Classesapp.GrpAndUserRepository;
import com.example.wishlist.Classesapp.GrpRepository;

public class CreateGroupActivity extends AppCompatActivity {

    private View.OnClickListener CreatGRP_listener=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            GrpRepository grpRepository=new GrpRepository(getApplicationContext());

            if (name_group.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),"Please fills Details", Toast.LENGTH_LONG).show();
            }else {
                String searcher=searchfriend.getQuery().toString();
                namegrp=name_group.getText().toString().trim();
                if (searcher.isEmpty()){
                    //Grp grp=new Grp(namegrp);
                    //grpRepository.InsertTask(grp);
                }else {
                    GrpAndUserRepository grpAndUserRepository=new GrpAndUserRepository(getApplicationContext());
                    //Grp grp=new Grp(namegrp);
                    //grpRepository.InsertTask(grp);
                    //grpAndUserRepository.InsertTask();
                    //plus rajouter le premier membre
                }

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
}
