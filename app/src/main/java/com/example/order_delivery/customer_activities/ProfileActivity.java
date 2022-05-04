package com.example.order_delivery.customer_activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.order_delivery.R;
import com.example.order_delivery.customer_activities.WalletActivity;
import com.example.order_delivery.local_model.CurrentUserInfo;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvProfileName;
    private TextView tvProfileVipStatus;
    private TextView tvProfileCurrentBalance;
    private TextView tvProfileUserWarning;
    private double addAmount;
    public static int REQUEST_CODE = 21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tvProfileName = findViewById(R.id.tvProfileName);
        tvProfileCurrentBalance = findViewById(R.id.tvProfileCurrentBalance);
        tvProfileVipStatus = findViewById(R.id.tvProfileVipStatus);
        tvProfileUserWarning = findViewById(R.id.tvProfileUserWarning);

        tvProfileName.setText("Username: " + CurrentUserInfo.currentUserName);
        tvProfileCurrentBalance.setText("Current Balance: " + Double.toString(CurrentUserInfo.currentUserBalance));
        tvProfileUserWarning.setText("Current Warning: " + Integer.toString(CurrentUserInfo.currentUserWarning));
        if (CurrentUserInfo.currentUserVip == true){
            tvProfileVipStatus.setText("Current Vip Status: Is a vip");
        }
        else {
            tvProfileVipStatus.setText("Current Vip Status: Is not a vip");
        }

    }

    public void onProfileAddMoneyClick(View view) {
        Intent intent = new Intent(this, WalletActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            //get data from intent (address)
            //update string
            addAmount = data.getExtras().getDouble("addAmount");
            CurrentUserInfo.currentUserBalance += addAmount;
            CurrentUserInfo.currentUser.setBalance(CurrentUserInfo.currentUserBalance);
            CurrentUserInfo.currentUser.saveInBackground();
            tvProfileCurrentBalance.setText(Double.toString(CurrentUserInfo.currentUserBalance));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void onProfileBackMenuClick(View view) {
        finish();
    }
}