package com.example.wishlist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wishlist.Classesapp.Grp;
import com.example.wishlist.Classesapp.GrpAndUserRepository;
import com.example.wishlist.Classesapp.ListAndProduct;
import com.example.wishlist.Classesapp.Wishlist;

import java.util.ArrayList;

public class GrpAdapter extends RecyclerView.Adapter<GrpAdapter.MyViewHolder> {

    private ArrayList<Grp> dataset;
    Context ActivityContext;
    Context ApplicationContext;


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView card_viewgrp_name;
        TextView card_view_grp_nbmembres;
        Button card_viewgrp_title;
        LinearLayout ll_card_group;

        public MyViewHolder(View itemView){
            super(itemView);
            this.card_viewgrp_name=itemView.findViewById(R.id.tv_card_view_group_name);
            this.card_view_grp_nbmembres=itemView.findViewById(R.id.tv_card_view_group_nbmemebres);
            this.card_viewgrp_title=itemView.findViewById(R.id.btn_card_view_group_title);
            this.ll_card_group=itemView.findViewById(R.id.ll_card_group);

        }
    }

    public GrpAdapter(ArrayList<Grp> dataset, Context activityContext, Context applicationContext) {
        this.dataset = dataset;
        ActivityContext = activityContext;
        ApplicationContext = applicationContext;
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
        TextView card_viewgrp_name = holder.card_viewgrp_name;
        TextView card_view_grp_nbmembres = holder.card_view_grp_nbmembres;
        Button card_viewgrp_title= holder.card_viewgrp_title;
        LinearLayout ll_card_grp = holder.ll_card_group;

        GrpAndUserRepository grpAndUserRepository = new GrpAndUserRepository(ApplicationContext);

        int id_group=dataset.get(position).getID();
        int size_grp=grpAndUserRepository.getSizeGroup(id_group);

        card_viewgrp_name.setText(dataset.get(position).getName());
        card_view_grp_nbmembres.setText(String.valueOf(size_grp));

        final int final_pos = position;
        final int final_id_gorup = id_group;

        if((Activity) ActivityContext instanceof ViewGroupsActivity)
        {
           //setOnClickUpdateGroup(ll_card_grp,final_pos,final_id_gorup);
        }

    }

    ////Pour ViewWishlistProduct
    private void setOnClickUpdateGroup(final LinearLayout ll, final int pos, final  int id_group)
    {
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }
    ///

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}


