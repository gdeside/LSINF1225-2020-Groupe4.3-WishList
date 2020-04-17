package com.example.wishlist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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


    private View.OnClickListener Update_Listener = new View.OnClickListener() {
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
    };

    private View.OnClickListener Delete_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            name = edt_wishlist_name.getText().toString().trim();
            option = public_btn.isChecked();
            description = edt_wishlist_description.getText().toString().trim();

            WishlistRepository wishlistRepository = new WishlistRepository(getApplicationContext());
            Wishlist wishlist = new Wishlist(name,option,getUsername(),description);
            wishlist.setNum_list(wishlist_num);
            generate_delete_dialog(wishlist);

            ////openViewWishlistsActivity();

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_wishlist);

        Button UpdateWishlist_btn = findViewById(R.id.UpdateWishlist_btn);
        UpdateWishlist_btn.setOnClickListener(Update_Listener);

        Button DeleteWishlist_btn = findViewById(R.id.DeleteWishlist_btn);
        DeleteWishlist_btn.setOnClickListener(Delete_Listener);




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

    public void generate_delete_dialog(Wishlist wishlist)
    {
        final Wishlist wishlist_delete = wishlist;

        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateWishlistActivity.this); //do not write getapplicationContextg
        builder.setTitle("WARNING");
        builder.setMessage("Are you sure to delete ?" + "\n Name : " + wishlist_delete.getName() + "\n Description : " + wishlist_delete.getDescription());
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                WishlistRepository wishlistRepository = new WishlistRepository(getApplicationContext());
                wishlistRepository.DeleteTask(wishlist_delete);

                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setIcon(android.R.drawable.ic_delete);


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
