package com.example.order_delivery.customer_activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.order_delivery.model.CheckoutList;
import com.example.order_delivery.R;
import com.example.order_delivery.adapters.checkOutAdapter;
import com.example.order_delivery.local_model.CartItem;
import com.example.order_delivery.local_model.CurrentUserInfo;

import java.util.ArrayList;

public class CheckOut extends AppCompatActivity {
    private double totalCost;
    private TextView tvTotalCost;
    private RecyclerView rvCheckoutList;
    private checkOutAdapter adapter;
    private String address;
    private String list = "";

    public static final int REQUEST_CODE = 20;
    public static ArrayList<CartItem> cartItemList = new ArrayList<>();
//    public static JSONArray testJson = new JSONArray();
    public static final String TAG = "CHECKOUT ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        tvTotalCost = findViewById(R.id.tvTotalCost);
        rvCheckoutList = findViewById(R.id.rvCheckoutList);
        totalCost = 0;
        adapter = new checkOutAdapter(this, cartItemList);
        rvCheckoutList.setAdapter(adapter);
        rvCheckoutList.setLayoutManager(new LinearLayoutManager(this));
        setTotalCost();
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
    public void onCheckoutClick(View view) {
        processOrder(true);
    }




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
//        }
    }

    public void onPickUpClick(View view) {
        processOrder(false);
    }
    public void processOrder(boolean delivery){
        if (totalCost > CurrentUserInfo.currentUserBalance) {
            Toast.makeText(CheckOut.this, "Total cost greater than balance: Receiving a warning", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(this, AddressActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
            else{
                saveCheckoutItem(delivery);
                cartItemList.clear();
                finish();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            //get data from intent (address)
            //update string
            address = data.getExtras().getString("address");
            System.out.println(address + "in checkout");
            saveCheckoutItem(true);
            cartItemList.clear();
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}