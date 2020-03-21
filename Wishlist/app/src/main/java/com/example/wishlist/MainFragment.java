package com.example.wishlist;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        View.OnClickListener s1 = Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_profileFragment);
        Button button1 = view.findViewById(R.id.profile_btn);
        button1.setOnClickListener(s1);

        View.OnClickListener s2= Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_wishlistsFragment);
        Button button2 = view.findViewById(R.id.wishlists_btn);
        button2.setOnClickListener(s2);

        View.OnClickListener s3 = Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_friendlistFragment);
        Button button3 = view.findViewById(R.id.friendlist_btn);
        button3.setOnClickListener(s3);
    }


}
