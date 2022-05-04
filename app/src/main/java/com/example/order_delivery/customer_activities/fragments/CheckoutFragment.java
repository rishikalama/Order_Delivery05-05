package com.example.order_delivery.customer_activities.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.order_delivery.R;
import com.example.order_delivery.adapters.checkOutAdapter;
import com.example.order_delivery.customer_activities.AddressActivity;
import com.example.order_delivery.local_model.CartItem;
import com.example.order_delivery.local_model.CurrentUserInfo;
import com.example.order_delivery.model.CheckoutList;
import com.example.order_delivery.model.Notification;

import java.util.ArrayList;


public class CheckoutFragment extends Fragment {

    private double totalCost;
    private TextView tvTotalCost;
    private RecyclerView rvCheckoutList;
    private checkOutAdapter adapter;
    private String address;
    private String list = "";
    private Button btnDelivery;
    private Button btnPickup;
    public static final int REQUEST_CODE = 20;
    public static ArrayList<CartItem> cartItemList = new ArrayList<>();
    public static final String TAG = "CHECKOUT Fragment";

    public CheckoutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTotalCost = view.findViewById(R.id.tvTotalCost);
        rvCheckoutList = view.findViewById(R.id.rvCheckoutList);
        totalCost = 0;
        adapter = new checkOutAdapter(getContext(), cartItemList);
        rvCheckoutList.setHasFixedSize(true);
        rvCheckoutList.setAdapter(adapter);
        rvCheckoutList.setLayoutManager(new LinearLayoutManager(getContext()));
        btnDelivery = view.findViewById(R.id.btnDelivery);
        btnPickup = view.findViewById(R.id.btnPickup);
        //set on click listener for button

        btnPickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processOrder(false);
            }
        });

        btnDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processOrder(true);
            }
        });
        setTotalCost();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checkout, container, false);
    }

    public void setTotalCost(){
        //calculate price
        String temp;
        for(int i = 0; i < cartItemList.size(); i++){
            totalCost += cartItemList.get(i).getQuantity() * cartItemList.get(i).getPrice();
            if (i != cartItemList.size() - 1){
                temp = cartItemList.get(i).getName() + ":" + cartItemList.get(i).getQuantity() + ",";
            }
            else{
                temp = cartItemList.get(i).getName() + ":" + cartItemList.get(i).getQuantity();
            }
            list += temp;
        }
        System.out.println(list);
        tvTotalCost.setText("Your total: " + "$" + Double.toString(totalCost));
    }
//    public void onCheckoutClick(View view) {
//        processOrder(true);
//    }




    public void saveCheckoutItem(boolean delivery) {
//        for (int i = 0; i < cartItemList.size(); i++){
        CheckoutList checkoutList = new CheckoutList();
//            checkoutList.setQuantity(cartItemList.get(i).getQuantity());
//            checkoutList.setItem(cartItemList.get(i).getName());
//            checkoutList.setPrice(cartItemList.get(i).getPrice());
//            checkoutList.setImageURL(cartItemList.get(i).getImageUrl());

        checkoutList.setDelivery(delivery);
        checkoutList.setList(list);
        if(delivery != false){
            checkoutList.setAddress(address);
        }
        checkoutList.saveInBackground();

//        Notification notification = new Notification();
//        notification.setToUsername("manager");
//        notification.setMessage("message to manager");
//        notification.setSubject("test");
//        notification.setFromUser(CurrentUserInfo.currentUserName);
//        notification.saveInBackground();
//        }

    }
//
//    public void onPickUpClick(View view) {
//        processOrder(false);
//    }
    public void processOrder(boolean delivery){
        if (totalCost > CurrentUserInfo.currentUserBalance) {
            Toast.makeText(getContext(), "Total cost greater than balance: Receiving a warning", Toast.LENGTH_SHORT).show();
            CurrentUserInfo.currentUserWarning += 1;
            CurrentUserInfo.currentUser.setWarning(CurrentUserInfo.currentUserWarning);
            CurrentUserInfo.currentUser.saveInBackground();
            System.out.println(CurrentUserInfo.currentUserWarning + "current warning");
        }
        else{
            CurrentUserInfo.currentUserBalance -= totalCost;
            CurrentUserInfo.currentUser.setBalance(CurrentUserInfo.currentUserBalance);
            System.out.println(CurrentUserInfo.currentUserBalance + "current balance");
            CurrentUserInfo.currentUser.saveInBackground();
            if(delivery == true){
                Intent intent = new Intent(getContext(), AddressActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
            else{
                saveCheckoutItem(delivery);
                cartItemList.clear();
                totalCost = 0;
                tvTotalCost.setText("$ " + String.valueOf(totalCost));
                adapter.notifyDataSetChanged();
            }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            //get data from intent (address)
            //update string
            address = data.getExtras().getString("address");
            System.out.println(address + "in checkout");
            saveCheckoutItem(true);
            cartItemList.clear();
            totalCost = 0;
            tvTotalCost.setText("$ " + String.valueOf(totalCost));
            adapter.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}