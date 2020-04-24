package com.example.wishlist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wishlist.Classesapp.ListAndProduct;
import com.example.wishlist.Classesapp.ListAndProductRepository;
import com.example.wishlist.Classesapp.Product;
import com.example.wishlist.Classesapp.Wishlist;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {


    private ArrayList<Product> dataset;
    Context context;
    private int Num_List;



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_card_view_product_name;
        TextView tv_card_view_product_price;
        ImageView img_card_view_product_note;
        Button btn_card_view_product_title;
        LinearLayout ll_card_product;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tv_card_view_product_name = (TextView) itemView.findViewById(R.id.tv_card_view_product_name);
            this.tv_card_view_product_price = (TextView) itemView.findViewById(R.id.tv_card_view_product_price);
            this.img_card_view_product_note = (ImageView) itemView.findViewById(R.id.img_card_view_product_note);
            this.btn_card_view_product_title = (Button) itemView.findViewById(R.id.btn_card_view_product_title);
            this.ll_card_product =(LinearLayout) itemView.findViewById(R.id.ll_card_product);
        }

    }

    public ProductAdapter(ArrayList<Product> dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
    }

    public ProductAdapter(ArrayList<Product> dataset, Context context, int num_List) {
        this.dataset = dataset;
        this.context = context;
        Num_List = num_List;
    }

    @NonNull
    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product, parent, false);

        ProductAdapter.MyViewHolder myViewHolder = new ProductAdapter.MyViewHolder(view);


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.MyViewHolder holder, int position) {
        TextView tv_card_view_product_name = holder.tv_card_view_product_name;
        TextView tv_card_view_product_price = holder.tv_card_view_product_price;
        ImageView img_card_view_privacy = holder.img_card_view_product_note;
        Button btn_card_view_title = holder.btn_card_view_product_title;
        LinearLayout ll_card_product = holder.ll_card_product;

        tv_card_view_product_name.setText(dataset.get(position).getName() + "");
        tv_card_view_product_price.setText(Integer.toString(dataset.get(position).getPrix()));

        final int pos = position;
        final int num = Num_List;

        if((Activity) context instanceof WishlistAddProductActivity)
        {
            setOnClickAdd(ll_card_product,pos,num);
        }

    }

    private void setOnClickAdd(final LinearLayout linearLayout, final int pos,final int num)
    {
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = dataset.get(pos).getName();


                ListAndProductRepository listAndProductRepository = new ListAndProductRepository(context);
                if (listAndProductRepository.isObjectInList(name, num)) {
                    Toast.makeText(context,"Product Already in list",Toast.LENGTH_LONG).show();
                } else
                {
                    ListAndProduct listAndProduct = new ListAndProduct(Num_List, name);
                    listAndProductRepository.InsertTask(listAndProduct);
                }
            }
        });
    }
    @Override
    public int getItemCount(){
        return dataset.size();
    }
}
