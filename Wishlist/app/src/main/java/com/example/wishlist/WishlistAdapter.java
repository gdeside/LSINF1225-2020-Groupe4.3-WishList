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

import com.example.wishlist.Classesapp.Wishlist;

import java.util.ArrayList;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.MyViewHolder> {

    private ArrayList<Wishlist> dataset;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_card_view_name;
        TextView tv_card_view_creator;
        ImageView img_card_view_privacy;
        Button btn_card_view_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tv_card_view_name = (TextView) itemView.findViewById(R.id.tv_card_view_wishlist_name);
            this.tv_card_view_creator = (TextView) itemView.findViewById(R.id.tv_card_view_wishlist_creator);
            this.img_card_view_privacy = (ImageView) itemView.findViewById(R.id.img_card_view_wishlist_privacy);
            this.btn_card_view_title = (Button) itemView.findViewById(R.id.btn_card_view_wishlist_title);
        }

    }

    // Constructeur
    public WishlistAdapter(ArrayList<Wishlist> data, Context context) {
        this.dataset = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_wishlist, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        TextView tv_card_view_name = holder.tv_card_view_name;
        TextView tv_card_view_creator = holder.tv_card_view_creator;
        ImageView img_card_view_privacy = holder.img_card_view_privacy;
        Button btn_card_view_title = holder.btn_card_view_title;

        tv_card_view_name.setText(dataset.get(position).getName() + "");
        tv_card_view_creator.setText(dataset.get(position).getCreator() + "");

        if (dataset.get(position).getOption())
            img_card_view_privacy.setImageResource(R.drawable.cadenas2);
        else
            img_card_view_privacy.setImageResource(R.drawable.cadenas1);


    }

    @Override
    public int getItemCount(){
        return dataset.size();
    }
}
