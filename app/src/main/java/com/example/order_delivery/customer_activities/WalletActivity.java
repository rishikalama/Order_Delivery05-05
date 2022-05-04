package com.example.order_delivery.customer_activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.order_delivery.R;
import com.google.android.material.textfield.TextInputEditText;

public class WalletActivity extends AppCompatActivity {

    private double amount;
    private TextInputEditText inputText;
    private TextView balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        inputText = (TextInputEditText) findViewById(R.id.input);
        balance = (TextView) findViewById(R.id.text_balance);
    }

    public void onAddMoneyClick(View view) {
        String text = inputText.getText().toString();
        balance.setText(text);
        amount = Double.parseDouble(text);
        Intent intent = new Intent();
        intent.putExtra("addAmount", amount);
        setResult(RESULT_OK, intent);
        finish();
    }
}
