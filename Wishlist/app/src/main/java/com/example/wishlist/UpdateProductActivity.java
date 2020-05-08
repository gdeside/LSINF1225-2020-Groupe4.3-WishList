package com.example.wishlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wishlist.Classesapp.Product;
import com.example.wishlist.Classesapp.ProductRepository;

public class UpdateProductActivity extends AppCompatActivity {

    EditText product_price,product_lien,product_categorie;
    TextView product_name;
    RatingBar product_note;
    Button save_change;
    String name,lien,categorie;
    int prix;
    float note;

    private View.OnClickListener SaveChange_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ProductRepository productRepository = new ProductRepository(getApplicationContext());

            prix = Integer.parseInt(product_price.getText().toString());
            note = product_note.getRating();
            categorie = product_categorie.getText().toString().trim();
            lien = product_lien.getText().toString().trim();

            Product product = new Product(name,prix);
            product.setCategorie(categorie);
            product.setLien(lien);
            product.setNote(note);

            productRepository.UpdateTask(product);
            openViewProductActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        ///------------------- Set EditTexts, Rating Bar and textView ------------------------------
        product_categorie = findViewById(R.id.edt_update_product_categorie);
        product_lien = findViewById(R.id.edt_update_product_lien);
        product_price = findViewById(R.id.edt_update_product_price);
        product_note = findViewById(R.id.rb_update_product_note);
        product_name = findViewById(R.id.tv_update_product_name);
        ///-----------------------------------------------------------------------------------------

        ///--------------------------- Set Button --------------------------------------------------
        save_change= findViewById(R.id.btn_update_product_save);
        save_change.setOnClickListener(SaveChange_Listener);
        ///-----------------------------------------------------------------------------------------

        ///------------------------- Set variables -------------------------------------------------
        Bundle data = getIntent().getExtras();
        if(data != null)
        {
            name = data.getString("Product_name");
            lien = data.getString("Product_lien","");
            prix = data.getInt("Product_prix");
            categorie = data.getString("Product_categorie","");
            note = data.getFloat("Product_note",0);
        }
        ///-----------------------------------------------------------------------------------------

        ///---------------------------- Set EditTexts + Name ---------------------------------------
        product_name.setText(name);
        product_categorie.setText(categorie);
        product_lien.setText(lien);
        product_note.setRating(note);
        product_price.setText(Integer.toString(prix));
        ///-----------------------------------------------------------------------------------------
    }

    private void openViewProductActivity()
    {
        Intent intent = new Intent(getApplicationContext(),ViewProductActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
    }
}
