package com.example.order_delivery.manager_activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.order_delivery.R;
import com.google.android.material.textfield.TextInputEditText;

public class Manager extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_view);
    }

    public void onGoToCheckOrders(View view) {
        Intent i = new Intent(this, OrderList.class);
        startActivity(i);
    }

    public void onGoToCustDirect(View view) {
        Intent i = new Intent(this, CustList.class);
        startActivity(i);
    }

    public void onGoToEmployDirect(View view) {
        Intent i = new Intent(this, EmployList.class);
        startActivity(i);
    }
}
