package com.example.order_delivery.customer_activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import com.example.order_delivery.R;
import com.example.order_delivery.adapters.sz_item_custAdapter;
import com.example.order_delivery.customer_activities.CheckOut;
import com.example.order_delivery.customer_activities.ProfileActivity;
import com.example.order_delivery.local_model.CurrentUserInfo;
import com.example.order_delivery.model.sz_item_cust;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    private RecyclerView rvMenu;
    private sz_item_custAdapter adapter;
    private List<sz_item_cust> all_item;
    private List<sz_item_cust> temp_item;
    private CountDownTimer countDownTimer;
    public static final String TAG = "MenuActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        System.out.println(CurrentUserInfo.currentUserName);

        rvMenu = findViewById(R.id.rvMenu);
        all_item = new ArrayList<>();
        temp_item =  new ArrayList<>();
        queryPosts();
        adapter = new sz_item_custAdapter(this, all_item);
        rvMenu.setAdapter(adapter);
        rvMenu.setLayoutManager(new LinearLayoutManager(this));
//        refreshView();
    }

    private void queryPosts() {
        ParseQuery<sz_item_cust> query = ParseQuery.getQuery(sz_item_cust.class);
        query.findInBackground(new FindCallback<sz_item_cust>() {
            @Override
            public void done(List<sz_item_cust> items, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (sz_item_cust item: items){
                    Log.i(TAG, "item:" + item.getItemName());
                }
                all_item.addAll(items);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void onGoToCheckOutClick(View view) {
        Intent i = new Intent(this, CheckOut.class);
        startActivity(i);
    }

    public void onProfileClick(View view) {
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

//    public void refreshView() {
//        countDownTimer = new CountDownTimer(30000, 1000) {
//            @Override
//            public void onTick(long l) {
//
//            }
//
//            @Override
//            public void onFinish() {
//                System.out.println("finish timer");
//                int temp = all_item.size();
//                queryPosts2();
//                if(temp_item.size() != 0){
//                    all_item.addAll(temp_item.subList(temp, temp_item.size()));
//                    adapter.notifyDataSetChanged();
//                }
//                refreshView();
//                //for smooth transtiion only add/ remove whats needed
//            }
//        }.start();
//    }
//    private void queryPosts2() {
//        ParseQuery<sz_item_cust> query = ParseQuery.getQuery(sz_item_cust.class);
//        query.findInBackground(new FindCallback<sz_item_cust>() {
//            @Override
//            public void done(List<sz_item_cust> items, ParseException e) {
//                if (e != null){
//                    Log.e(TAG, "Issue with getting posts", e);
//                    return;
//                }
//                for (sz_item_cust item: items){
//                    Log.i(TAG, "item:" + item.getItemName());
//                }
//                temp_item.addAll(items);
//            }
//        });
//    }
}