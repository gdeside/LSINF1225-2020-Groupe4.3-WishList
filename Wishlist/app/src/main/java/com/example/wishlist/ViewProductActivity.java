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

import com.example.wishlist.Classesapp.Product;
import com.example.wishlist.Classesapp.ProductRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ViewProductActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    ArrayList<Product> productArrayList;

    private View.OnClickListener CreateProduct_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openCreateProductActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        FloatingActionButton createProduct_btn = findViewById(R.id.floatingProduct);
        createProduct_btn.setOnClickListener(CreateProduct_listener);

        ProductRepository productRepository = new ProductRepository(getApplicationContext()); ///initiate Database to prefill with product


        ///---------------------------- Card Management --------------------------------------------
        recyclerView = (RecyclerView)findViewById(R.id.ViewProduct_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ///-----------------------------------------------------------------------------------------

        //-------------------------------------------- Bottom Navigation View ----------------------

        //Initalize and Assign Bottom Navigation View
        BottomNavigationView navigationView = findViewById(R.id.bottomNavigationView);

        //Set Wishlists Button Selected
        navigationView.setSelectedItemId(R.id.bottomNavProducts_btn);

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
                        //startActivity(new Intent(getApplicationContext(),ViewProductActivity.class));
                        //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
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
        new ViewProductActivity.LoadDataTask().execute();
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

            for(int i =0; i <productList.size();i++)
            {
                productArrayList.add(productList.get(i));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            ProductAdapter productAdapter = new ProductAdapter(productArrayList, -1,ViewProductActivity.this,getApplicationContext()); ///wishlist num == -1 because we're not in a list.
            recyclerView.setAdapter(productAdapter);
        }
    }

    public void openCreateProductActivity()
    {
        Intent intent = new Intent(this, CreateProductActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
    }


}
