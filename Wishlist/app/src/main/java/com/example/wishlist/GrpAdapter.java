package com.example.wishlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GrpAdapter extends RecyclerView.Adapter<GrpAdapter.MyViewHolder> {


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView card_viewgrp_name;
        TextView card_view_grp_nbmembres;
        Button card_viewgrp_tittle;

        public MyViewHolder(View itemView){
            super(itemView);
            this.card_viewgrp_name=itemView.findViewById(R.id.tv_card_view_group_name);
            this.card_view_grp_nbmembres=itemView.findViewById(R.id.tv_card_view_group_nbmemebres);
            this.card_viewgrp_tittle=itemView.findViewById(R.id.btn_card_view_group_title);

        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_group, parent, false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GrpAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}


