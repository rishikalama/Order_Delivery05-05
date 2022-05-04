package com.example.order_delivery.delivery_activities;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.order_delivery.R;

public class DeliveryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
    }

    public void onDeliveryBidClick(View view) {
        Intent intent = new Intent(this, DeliveryBid.class);
        startActivity(intent);
    }


    public void onCurrentDeliveryItemClick(View view) {
        Intent intent = new Intent(this, CurrentDelivery.class);
        startActivity(intent);
    }
}