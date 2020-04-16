package com.example.wishlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wishlist.Classesapp.Product;
import com.example.wishlist.Classesapp.Wishlist;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private ArrayList<Product> dataset;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_card_view_product_name;
        TextView tv_card_view_product_price;
        ImageView img_card_view_product_note;
        Button btn_card_view_product_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tv_card_view_product_name = (TextView) itemView.findViewById(R.id.tv_card_view_product_name);
            this.tv_card_view_product_price = (TextView) itemView.findViewById(R.id.tv_card_view_product_price);
            this.img_card_view_product_note = (ImageView) itemView.findViewById(R.id.img_card_view_product_note);
            this.btn_card_view_product_title = (Button) itemView.findViewById(R.id.btn_card_view_product_title);
        }

    }

    // Constructeur
    public ProductAdapter(ArrayList<Product> data, Context context) {
        this.dataset = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        TextView tv_card_view_product_name = holder.tv_card_view_product_name;
        TextView tv_card_view_product_price = holder.tv_card_view_product_price;
        ImageView img_card_view_privacy = holder.img_card_view_product_note;
        Button btn_card_view_title = holder.btn_card_view_product_title;;

        tv_card_view_product_name.setText(dataset.get(position).getName() + "");
        tv_card_view_product_price.setText(Integer.toString(dataset.get(position).getPrix()));




    }

    @Override
    public int getItemCount(){
        return dataset.size();
    }
}
