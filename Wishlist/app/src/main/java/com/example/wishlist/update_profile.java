package com.example.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class update_profile extends AppCompatActivity {

    Button btn_save_profil;
    ImageView photo_profil;
    EditText edit_identifiant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        //Button et edittext
        btn_save_profil=findViewById(R.id.save_profil);
        photo_profil=findViewById(R.id.edit_photoprofile);
        edit_identifiant=findViewById(R.id.profile_identifiant);
    }
}
