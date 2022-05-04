package com.example.order_delivery.delivery_activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.order_delivery.R;
import com.example.order_delivery.adapters.DeliveryBidAdapter;
import com.example.order_delivery.model.CompleteOrder;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class DeliveryBid extends AppCompatActivity {

    private RecyclerView rvDeliveryBid;
    private DeliveryBidAdapter deliveryBidAdapter;
    private List<CompleteOrder> all_item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_bid);
        rvDeliveryBid = findViewById(R.id.rvDeliveryBid);
        all_item = new ArrayList<>();


        deliveryBidAdapter = new DeliveryBidAdapter(this, all_item);
        rvDeliveryBid.setAdapter(deliveryBidAdapter);
        rvDeliveryBid.setLayoutManager(new LinearLayoutManager(this));
        queryPosts();
    }

    private void queryPosts() {
        ParseQuery<CompleteOrder> query = ParseQuery.getQuery("CompleteOrder");
        query.whereEqualTo("deliveryPerson", "not assigned");
        query.findInBackground(new FindCallback<CompleteOrder>() {
            @Override
            public void done(List<CompleteOrder> items, ParseException e) {
                if (e != null){
                    Log.e("Delivery Bid Activity", "Issue with getting posts", e);
                    return;
                }
                for (CompleteOrder item: items){
                    Log.i("Delivery Bid Activity", "item:" + item.getName());
                }
                all_item.addAll(items);
                deliveryBidAdapter.notifyDataSetChanged();
            }
        });
    }
}