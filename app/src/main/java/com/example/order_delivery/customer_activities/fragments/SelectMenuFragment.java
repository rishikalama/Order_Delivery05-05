package com.example.order_delivery.customer_activities.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.order_delivery.R;
import com.example.order_delivery.adapters.sz_item_custAdapter;

import java.util.ArrayList;


public class SelectMenuFragment extends Fragment {


    private ImageView ivKoreanFood;
    private ImageView ivDessert;
    private String category;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivKoreanFood = view.findViewById(R.id.ivKoreanFood);
        ivDessert = view.findViewById(R.id.ivDessert);

        ivKoreanFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = "koreanFood";
                AppCompatActivity activity = (AppCompatActivity) view.getContext();

                // Create the CategoryDetailsFragment
                Fragment menuFragment = new MenuFragment();
                Bundle bundle = new Bundle();
                bundle.putString("category", category);
                menuFragment.setArguments(bundle);

                activity.
                        getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flMenuContainer, menuFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        ivDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = "dessert";
                AppCompatActivity activity = (AppCompatActivity) view.getContext();

                // Create the CategoryDetailsFragment
                Fragment menuFragment = new MenuFragment();
                Bundle bundle = new Bundle();
                bundle.putString("category", category);
                menuFragment.setArguments(bundle);

                activity.
                        getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flMenuContainer, menuFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_menu, container, false);
    }
}