package com.example.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.wishlist.Classesapp.UserRepository;
import com.example.wishlist.Classesapp.ProductRepository;
import com.example.wishlist.Classesapp.WishlistRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openWelcomescreen();
    }

    public void openWelcomescreen()
    {
        Intent intent = new Intent(this, Welcome_Screen.class);

        // Clear back stack : Empeche l'utilisateur de revenir cet écra,
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(0,0); // Animation entre écran
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    }

}
