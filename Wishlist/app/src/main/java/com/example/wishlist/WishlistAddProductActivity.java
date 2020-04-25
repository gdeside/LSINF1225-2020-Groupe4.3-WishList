package com.example.wishlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.wishlist.Classesapp.ListAndProductRepository;
import com.example.wishlist.Classesapp.Product;
import com.example.wishlist.Classesapp.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WishlistAddProductActivity extends AppCompatActivity {

    String name, description;
    int wishlist_num;
    Boolean option;
    EditText edt_AddProduct_search;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    ArrayList<Product> productArrayList,productArrayList_search;
    ProductAdapter productAdapter;

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

        edt_AddProduct_search = findViewById(R.id.edt_AddProduct_search);
        edt_AddProduct_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text=s.toString();
                Filter(text);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


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
                if(product.getName().toString().toLowerCase(Locale.getDefault()).contains(charText))
                {
                   productArrayList.add(product);
                }
            }
            productAdapter.notifyDataSetChanged();
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
