package com.example.wishlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.wishlist.Classesapp.ListAndProductRepository;
import com.example.wishlist.Classesapp.ListAndUserRepository;
import com.example.wishlist.Classesapp.Product;
import com.example.wishlist.Classesapp.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ViewWishlistProductActivity extends AppCompatActivity {

    String name, description;
    int wishlist_num;
    Boolean option;
    ListAndUserRepository listAndUserRepository;

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

    private View.OnClickListener WishlistAddProduct_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(listAndUserRepository.isCreator(getUsername(),wishlist_num))
            {
                openWishlistAddProductActivity();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"What are you doing? NOT YOUR LIST!",Toast.LENGTH_LONG).show();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_wishlist_product);

        Button SettingsWishlist_btn = findViewById(R.id.SettingsWishlist_btn);
        SettingsWishlist_btn.setOnClickListener(SettingsWishlist_listener);

        Button AddProduct_btn = findViewById(R.id.AddProductWishlist_btn);
        AddProduct_btn.setOnClickListener(WishlistAddProduct_listener);

        listAndUserRepository = new ListAndUserRepository(getApplicationContext());

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

            ProductAdapter productAdapter = new ProductAdapter(productArrayList,wishlist_num, ViewWishlistProductActivity.this,getApplicationContext()); //No num list == -1, constructor shadowing seemms to not work
            recyclerView.setAdapter(productAdapter);
        }
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        new ViewWishlistProductActivity.LoadDataTask().execute();
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

    public void openWishlistAddProductActivity()
    {
        Intent intent = new Intent(this, WishlistAddProductActivity.class);
        intent.putExtra("wishlist_name",name);
        intent.putExtra("wishlist_description",description);
        intent.putExtra("wishlist_option",option);
        intent.putExtra("wishlist_num",wishlist_num);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
    }

    public void Reload()
    {
        new ViewWishlistProductActivity.LoadDataTask().execute();
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

}
