package com.example.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.wishlist.Classesapp.Wishlist;
import com.example.wishlist.Classesapp.WishlistRepository;

public class UpdateWishlistActivity extends AppCompatActivity {

    EditText edt_wishlist_name,edt_wishlist_description;
    RadioButton public_btn, private_btn;


    String name, description;
    int wishlist_num;
    Boolean option;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_wishlist);

        Button UpdateWishlist_btn = findViewById(R.id.UpdateWishlist_btn);
        UpdateWishlist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_wishlist_name.getText().toString().isEmpty() ) {

                    Toast.makeText(getApplicationContext(),"Please fill name",Toast.LENGTH_LONG).show();

                } ///Create Wishlist
                else {
                    name = edt_wishlist_name.getText().toString().trim();
                    option = public_btn.isChecked();
                    description = edt_wishlist_description.getText().toString().trim();

                    WishlistRepository wishlistRepository = new WishlistRepository(getApplicationContext());
                    Wishlist wishlist = new Wishlist(name,option,getUsername(),description);
                    wishlist.setNum_list(wishlist_num);
                    wishlistRepository.UpdateTask(wishlist);

                    openViewWishlistsActivity();
                }

            }
        });


        edt_wishlist_name = (EditText) findViewById(R.id.edt_wishlist_name);
        edt_wishlist_description = findViewById(R.id.edt_wishlist_description);
        public_btn = findViewById(R.id.rbtn_public);
        private_btn = findViewById(R.id.rbtn_private);

        ///Get values
        Bundle data = getIntent().getExtras();
        if(data != null)
        {
            name = data.getString("wishlist_name");
            description = data.getString("wishlist_description");
            option = data.getBoolean("wishlist_option");
            wishlist_num = data.getInt("wishlist_num");
        }

        //show values
        edt_wishlist_name.setText(name);
        edt_wishlist_description.setText(description);

        if(option)
            public_btn.setChecked(true);
        else
            private_btn.setChecked(true);



    }

    public String getUsername(){
        // Retrieving the value using its keys
        // the file name must be same in both saving
        // and retrieving the data
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);

        // The value will be default as empty string
        // because for the very first time
        // when the app is opened,
        // there is nothing to show
        return sh.getString("ID", "");
    }
    public void openViewWishlistsActivity()
    {
        Intent intent = new Intent(this, ViewWishlistsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
    }

}
