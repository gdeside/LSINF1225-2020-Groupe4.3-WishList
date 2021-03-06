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

import com.example.wishlist.Classesapp.ListAndProductRepository;
import com.example.wishlist.Classesapp.ListAndUser;
import com.example.wishlist.Classesapp.ListAndUserRepository;
import com.example.wishlist.Classesapp.Wishlist;
import com.example.wishlist.Classesapp.WishlistRepository;

public class UpdateWishlistActivity extends AppCompatActivity {

    EditText edt_wishlist_name,edt_wishlist_description;
    RadioButton public_btn, private_btn;
    Button UpdateWishlist_btn, DeleteWishlist_btn;
    String name, description;
    int wishlist_num;
    Boolean option;


    private View.OnClickListener Update_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /// If no name is given
            if(edt_wishlist_name.getText().toString().isEmpty() ) {
                Toast.makeText(getApplicationContext(),"Please fill name",Toast.LENGTH_LONG).show();
            }
            ///Create Wishlist
            else {
                name = edt_wishlist_name.getText().toString().trim();
                option = public_btn.isChecked();
                description = edt_wishlist_description.getText().toString().trim();

                WishlistRepository wishlistRepository = new WishlistRepository(getApplicationContext());
                Wishlist wishlist = new Wishlist(name,option,description);
                wishlist.setNum_list(wishlist_num);
                wishlistRepository.UpdateTask(wishlist);

                openViewWishlistProductActivity();
            }

        }
    };

    private View.OnClickListener Delete_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            name = edt_wishlist_name.getText().toString().trim();
            option = public_btn.isChecked();
            description = edt_wishlist_description.getText().toString().trim();

            Wishlist wishlist = new Wishlist(name,option,description);
            wishlist.setNum_list(wishlist_num);
            generate_delete_dialog(wishlist);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_wishlist);

        ///-------------------------- Set Buttons --------------------------------------------------
        UpdateWishlist_btn = findViewById(R.id.UpdateWishlist_btn);
        UpdateWishlist_btn.setOnClickListener(Update_Listener);

        DeleteWishlist_btn = findViewById(R.id.DeleteWishlist_btn);
        DeleteWishlist_btn.setOnClickListener(Delete_Listener);

        public_btn = findViewById(R.id.rbtn_public);
        private_btn = findViewById(R.id.rbtn_private);
        ///-----------------------------------------------------------------------------------------

        ///-------------------------- Set Variables ------------------------------------------------
        Bundle data = getIntent().getExtras();
        if(data != null)
        {
            name = data.getString("wishlist_name");
            description = data.getString("wishlist_description");
            option = data.getBoolean("wishlist_option");
            wishlist_num = data.getInt("wishlist_num");
        }
        ///-----------------------------------------------------------------------------------------

        ///-------------------------- Set TextView -------------------------------------------------
        edt_wishlist_name = (EditText) findViewById(R.id.edt_wishlist_name);
        edt_wishlist_description = findViewById(R.id.edt_wishlist_description);

        edt_wishlist_name.setText(name);
        edt_wishlist_description.setText(description);

        if(option)
            public_btn.setChecked(true);
        else
            private_btn.setChecked(true);
        ///-----------------------------------------------------------------------------------------
    }

    /// Give logged user ID
    public String getUsername(){
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
        return sh.getString("ID", "");
    }

    public void openViewWishlistProductActivity()
    {
        Intent intent = new Intent(this, ViewWishlistProductActivity.class);
        intent.putExtra("wishlist_name",name);
        intent.putExtra("wishlist_description",description);
        intent.putExtra("wishlist_option",option);
        intent.putExtra("wishlist_num",wishlist_num);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
    }

    public void openWishlistsActivity()
    {
        Intent intent = new Intent(this, WishlistsActivity.class);
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
                delete_wishlist(wishlist_delete);
                openWishlistsActivity();
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

    public void delete_wishlist(final Wishlist wishlist)
    {
        WishlistRepository wishlistRepository = new WishlistRepository(getApplicationContext());
        ListAndUserRepository listAndUserRepository = new ListAndUserRepository(getApplicationContext());
        ListAndProductRepository listAndProductRepository = new ListAndProductRepository(getApplicationContext());

        wishlistRepository.DeleteTask(wishlist);
        listAndUserRepository.DeleteWishlist(wishlist.getNum_list());
        listAndProductRepository.DeleteWishlist(wishlist.getNum_list());
    }

}
