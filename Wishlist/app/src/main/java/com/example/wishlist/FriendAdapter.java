package com.example.wishlist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wishlist.Classesapp.Friend;
import com.example.wishlist.Classesapp.FriendRepository;

import java.util.ArrayList;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.MyViewHolder> {

    private ArrayList<Friend> dataset;
    Context ActivityContext;
    Context ApplicationContext;
    FriendRepository friendRepository;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_card_view_nickname;
        TextView tv_card_view_username;
        Button btn_card_view_title;
        LinearLayout ll_card_friend;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tv_card_view_nickname = (TextView) itemView.findViewById(R.id.tv_card_view_friend_nickname);
            this.tv_card_view_username = (TextView) itemView.findViewById(R.id.tv_card_view_friend_username);
            this.btn_card_view_title = (Button) itemView.findViewById(R.id.btn_card_view_friend_title);
            this.ll_card_friend = (LinearLayout) itemView.findViewById(R.id.ll_card_friend);
        }

    }

    // Constructeur
    public FriendAdapter(ArrayList<Friend> dataset, Context activityContext, Context applicationContext) {
        this.dataset = dataset;
        ActivityContext = activityContext;
        ApplicationContext = applicationContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_friend, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        friendRepository = new FriendRepository(ApplicationContext);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        TextView tv_card_view_nickname = holder.tv_card_view_nickname;
        TextView tv_card_view_username = holder.tv_card_view_username;

        Button btn_card_view_title = holder.btn_card_view_title;
        LinearLayout ll_card_friend = holder.ll_card_friend;

        tv_card_view_nickname.setText(dataset.get(position).getSurname() + "");
        tv_card_view_username.setText(dataset.get(position).getId_ami() + "");

        ll_card_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Friend_ID = dataset.get(position).getId_ami();
                String User_ID = dataset.get(position).getId_user();
                if((Activity) ActivityContext instanceof FriendlistActivity)
                {
                    openFriendProfileActivity(Friend_ID);
                }
            }
        });


    }

    public void openFriendProfileActivity(String ID_Friend){
        Intent intent=new Intent(ActivityContext,FriendProfileActivity.class);
        intent.putExtra("ID_Friend",ID_Friend);
        ActivityContext.startActivity(intent);
    }

    @Override
    public int getItemCount(){
        return dataset.size();
    }
}
