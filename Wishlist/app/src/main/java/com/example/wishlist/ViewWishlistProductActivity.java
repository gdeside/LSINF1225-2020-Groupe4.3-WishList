package com.example.wishlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wishlist.Classesapp.ListAndProductRepository;
import com.example.wishlist.Classesapp.Product;
import com.example.wishlist.Classesapp.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ViewWishlistProductActivity extends AppCompatActivity {

    String name, description;
    int wishlist_num;
    Boolean option;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    ArrayList<Product> productArrayList;

    private View.OnClickListener SettingsWishlist_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openUpdateWishlistsActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_wishlist_product);

        Button SettingsWishlist_btn = findViewById(R.id.SettingsWishlist_btn);
        SettingsWishlist_btn.setOnClickListener(SettingsWishlist_listener);

        ///Get values
        Bundle data = getIntent().getExtras();
        if(data != null)
        {
            name = data.getString("wishlist_name");
            description = data.getString("wishlist_description");
            option = data.getBoolean("wishlist_option");
            wishlist_num = data.getInt("wishlist_num");
        }

        recyclerView = (RecyclerView)findViewById(R.id.ViewWishlistProduct_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        new ViewWishlistProductActivity.LoadDataTask().execute();

    }

    class LoadDataTask extends AsyncTask<Void,Void,Void>
    {
        ProductRepository productRepository;
        ListAndProductRepository listAndProductRepository;
        List<String> productName;
        List<Product> productList;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            productRepository= new ProductRepository(getApplicationContext());
            listAndProductRepository = new ListAndProductRepository(getApplicationContext());
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            productName = listAndProductRepository.getWishlistProductNames(wishlist_num);
            productArrayList = new ArrayList<>();

            for(int i =0; i <productName.size();i++)
            {
                String ProductID = productName.get(i);
                Product product = productRepository.getProductByID(ProductID);
                productArrayList.add(product);
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);

            ProductAdapter productAdapter = new ProductAdapter(productArrayList, ViewWishlistProductActivity.this);
            recyclerView.setAdapter(productAdapter);
        }
    }

    public void openUpdateWishlistsActivity()
    {
        Intent intent = new Intent(this, UpdateWishlistActivity.class);
        intent.putExtra("wishlist_name",name);
        intent.putExtra("wishlist_description",description);
        intent.putExtra("wishlist_option",option);
        intent.putExtra("wishlist_num",wishlist_num);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
    }

}
