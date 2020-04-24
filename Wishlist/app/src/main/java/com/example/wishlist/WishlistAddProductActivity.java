package com.example.wishlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.wishlist.Classesapp.ListAndProductRepository;
import com.example.wishlist.Classesapp.Product;
import com.example.wishlist.Classesapp.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class WishlistAddProductActivity extends AppCompatActivity {

    String name, description;
    int wishlist_num;
    Boolean option;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    ArrayList<Product> productArrayList;

    private View.OnClickListener openwis_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            openViewWishlistProductActivity();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist_add_product);

        ///Get values
        Bundle data = getIntent().getExtras();
        if(data != null)
        {
            name = data.getString("wishlist_name");
            description = data.getString("wishlist_description");
            option = data.getBoolean("wishlist_option");
            wishlist_num = data.getInt("wishlist_num");
        }

        recyclerView = (RecyclerView)findViewById(R.id.AddProduct_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        new WishlistAddProductActivity.LoadDataTask().execute();

    }

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

            ProductAdapter productAdapter = new ProductAdapter(productArrayList, WishlistAddProductActivity.this,wishlist_num);
            recyclerView.setAdapter(productAdapter);
        }




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
}
