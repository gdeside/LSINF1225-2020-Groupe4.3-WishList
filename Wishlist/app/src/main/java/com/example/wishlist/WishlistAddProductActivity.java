package com.example.wishlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.wishlist.Classesapp.ListAndProductRepository;
import com.example.wishlist.Classesapp.Product;
import com.example.wishlist.Classesapp.ProductRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WishlistAddProductActivity extends AppCompatActivity {

    String name, description;
    int wishlist_num;
    Boolean option;
    EditText edt_AddProduct_search;

    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    ArrayList<Product> productArrayList,productArrayList_search;
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist_add_product);

        ///------------------------ Set Variables --------------------------------------------------
        Bundle data = getIntent().getExtras();
        if(data != null)
        {
            name = data.getString("wishlist_name");
            description = data.getString("wishlist_description");
            option = data.getBoolean("wishlist_option");
            wishlist_num = data.getInt("wishlist_num");
        }
        ///-----------------------------------------------------------------------------------------

        ///--------------------------- Card Management ---------------------------------------------
        recyclerView = (RecyclerView)findViewById(R.id.AddProduct_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ///-----------------------------------------------------------------------------------------

        ///------------------------------- Search Bar -----------------------------------------------
        edt_AddProduct_search = findViewById(R.id.edt_AddProduct_search);
        edt_AddProduct_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text=s.toString();
                Filter(text);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        ///-----------------------------------------------------------------------------------------

        //-------------------------------------------- Bottom Navigation View ----------------------

        //Initalize and Assign Bottom Navigation View
        BottomNavigationView navigationView = findViewById(R.id.bottomNavigationView);

        //Set Wishlists Button Selected
        navigationView.setSelectedItemId(R.id.bottomNavHome_btn);

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
                        startActivity(new Intent(getApplicationContext(),ViewGroupsActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
                        return true;
                }
                return false;
            }
        });
        ///-----------------------------------------------------------------------------------------

        /// Load Data
        new WishlistAddProductActivity.LoadDataTask().execute();

    }

    /// Load Data
    class LoadDataTask extends AsyncTask<Void,Void,Void>
    {
        ProductRepository productRepository;
        List<Product> productList;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            productRepository= new ProductRepository(getApplicationContext());
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            productList = productRepository.getProducts();
            productArrayList = new ArrayList<>();
            productArrayList_search = new ArrayList<>();

            for(int i =0; i <productList.size();i++)
            {
                productArrayList.add(productList.get(i));
                productArrayList_search.add(productList.get(i));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            productAdapter = new ProductAdapter(productArrayList, wishlist_num,WishlistAddProductActivity.this,getApplicationContext());
            recyclerView.setAdapter(productAdapter);
        }
    }

    ///Filter wishlists by name
    public void Filter(String charText)
    {
        charText = charText.toLowerCase(Locale.getDefault());
        productArrayList.clear();

        if(charText.length() == 0)
            productArrayList.addAll(productArrayList_search);
        else
        {
            for( Product product : productArrayList_search)
            {
                if(product.getName().toLowerCase(Locale.getDefault()).contains(charText))
                {
                   productArrayList.add(product);
                }
            }
            productAdapter.notifyDataSetChanged();
        }
    }

}
