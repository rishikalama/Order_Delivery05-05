package com.example.order_delivery.customer_activities.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.order_delivery.R;
import com.example.order_delivery.customer_activities.WalletActivity;
import com.example.order_delivery.local_model.CurrentUserInfo;

public class CustProfileFragment extends Fragment {


    private TextView tvProfileName;
    private TextView tvProfileVipStatus;
    private TextView tvProfileCurrentBalance;
    private TextView tvProfileUserWarning;
    private Button btnProfileAddMoney;
    private double addAmount;
    public static int REQUEST_CODE = 21;

    public CustProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvProfileName = view.findViewById(R.id.tvProfileName);
        tvProfileCurrentBalance = view.findViewById(R.id.tvProfileCurrentBalance);
        tvProfileVipStatus = view.findViewById(R.id.tvProfileVipStatus);
        tvProfileUserWarning = view.findViewById(R.id.tvProfileUserWarning);
        btnProfileAddMoney = view.findViewById(R.id.btnProfileAddMoney);

        btnProfileAddMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "add money click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), WalletActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cust_profile, container, false);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
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
}