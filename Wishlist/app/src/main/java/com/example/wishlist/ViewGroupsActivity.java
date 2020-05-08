package com.example.wishlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.wishlist.Classesapp.Grp;
import com.example.wishlist.Classesapp.GrpAndUser;
import com.example.wishlist.Classesapp.GrpAndUserRepository;
import com.example.wishlist.Classesapp.GrpRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ViewGroupsActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    ArrayList<Grp> grpArrayList;

    private View.OnClickListener CreateGrp_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openCreateGroupActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_groups);

        FloatingActionButton createGrp_btn = findViewById(R.id.floatingGroup);
        createGrp_btn.setOnClickListener(CreateGrp_listener);



       
        
        ///---------------------------- Card Management --------------------------------------------
        recyclerView = (RecyclerView)findViewById(R.id.ViewGroup_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ///-----------------------------------------------------------------------------------------

        //-------------------------------------------- Bottom Navigation View ----------------------

        //Initalize and Assign Bottom Navigation View
        BottomNavigationView navigationView = findViewById(R.id.bottomNavigationView);

        //Set Wishlists Button Selected
        navigationView.setSelectedItemId(R.id.bottomNavGroup_btn);

        ///Perform ItemSelected
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.bottomNavFriendlist_btn:
                        startActivity(new Intent(getApplicationContext(),FriendlistActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
                        return true;
                    case R.id.bottomNavProfile_btn:
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
                        return true;
                    case R.id.bottomNavHome_btn :
                        startActivity(new Intent(getApplicationContext(), WishlistsActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
                        return true;
                    case R.id.bottomNavProducts_btn :
                        startActivity(new Intent(getApplicationContext(),ViewProductActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
                        return true;
                    case R.id.bottomNavGroup_btn :
                        //startActivity(new Intent(getApplicationContext(),ViewGroupsActivity.class));
                        //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
                        return true;
                }
                return false;
            }
        });
        ///-----------------------------------------------------------------------------------------

        /// Load Data
        new LoadDataTask().execute();
    }

    /// Load Data
    class LoadDataTask extends AsyncTask<Void,Void,Void>
    {
        GrpRepository grpRepository;
        GrpAndUserRepository grpAndUserRepository;
        String username;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            grpRepository= new GrpRepository(getApplicationContext());
            grpAndUserRepository = new GrpAndUserRepository(getApplicationContext());
            username = getUsername();
            grpArrayList = new ArrayList<>();
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            List<GrpAndUser> grpAndUserList = grpAndUserRepository.getAllGrpAndUser_unsync(username);
            for(GrpAndUser grpAndUser : grpAndUserList)
            {
                Grp grp = grpRepository.getByID(grpAndUser.getId_grp());
                grpArrayList.add(grp);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            GrpAdapter grpAdapter = new GrpAdapter(grpArrayList,ViewGroupsActivity.this,getApplicationContext());
            recyclerView.setAdapter(grpAdapter);
        }
    }

    public void openCreateGroupActivity()
    {
        Intent intent = new Intent(this, CreateGroupActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
    }

    /// Give logged user ID
    public String getUsername(){
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
        return sh.getString("ID", "");
    }

    /// If you go back to this activity, reload the data
    @Override
    protected void onRestart(){
        super.onRestart();
        new ViewGroupsActivity.LoadDataTask().execute();
    }
}
