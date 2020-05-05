package com.example.wishlist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
                /*String wishlist_name = dataset.get(position).getName();
                String wishlist_creator = dataset.get(position).getCreator();
                String wishlist_description = dataset.get(position).getDescription();
                Boolean wishlist_option = dataset.get(position).getOption();
                int wishlist_num = dataset.get(position).getNum_list();

                Intent intent = new Intent(context,ViewWishlistProductActivity.class);
                intent.putExtra("wishlist_name",wishlist_name);
                intent.putExtra("wishlist_description",wishlist_description);
                intent.putExtra("wishlist_option",wishlist_option);
                intent.putExtra("wishlist_num",wishlist_num);

                context.startActivity(intent); */
                String Friend_ID = dataset.get(position).getId_ami();
                String User_ID = dataset.get(position).getId_user();
                if((Activity) ActivityContext instanceof FriendlistActivity)
                {
                    generate_change_nickname_dialog(User_ID, Friend_ID);
                }
            }
        });


    }

    public void generate_change_nickname_dialog(final String User_ID, final String Friend_ID)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(ActivityContext);
        final EditText edittext = new EditText(ActivityContext);

        ///alert.setMessage("Enter Your Message");
        alert.setTitle("Change Nickname ?");

        alert.setView(edittext);

        alert.setNeutralButton("View Profile", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });

        alert.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String nickname = edittext.getText().toString();
                Friend friend = new Friend(Friend_ID,User_ID,nickname);
                friendRepository.UpdateTask(friend);
                ((FriendlistActivity) ActivityContext).Reload();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });

        alert.show();
    }
    @Override
    public int getItemCount(){
        return dataset.size();
    }
}
