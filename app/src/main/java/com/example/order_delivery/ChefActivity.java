package com.example.order_delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.order_delivery.local_model.CurrentEmployeeInfo;

public class ChefActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef);

        System.out.println(CurrentEmployeeInfo.currentEmployeeName);
        System.out.println(CurrentEmployeeInfo.currentEmployeeTitle);
    }
}