package com.example.order_delivery.delivery_activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.order_delivery.R;
import com.example.order_delivery.adapters.CurrentDeliAdapter;
import com.example.order_delivery.model.CompleteOrder;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class CurrentDelivery extends AppCompatActivity {

    private RecyclerView rvCurrentDeliveryList;
    private CurrentDeliAdapter currentDeliAdapter;
    private List<CompleteOrder> all_item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_delivery);
        all_item = new ArrayList<>();
        rvCurrentDeliveryList = findViewById(R.id.rvCurrentDeliveryList);
        currentDeliAdapter = new CurrentDeliAdapter(this, all_item);
        rvCurrentDeliveryList.setAdapter(currentDeliAdapter);
        rvCurrentDeliveryList.setLayoutManager(new LinearLayoutManager(this));
        queryPosts();
    }

    private void queryPosts() {
        ParseQuery<CompleteOrder> query = ParseQuery.getQuery("CompleteOrder");
        //delivery1 test value set to current delivery person
        query.whereEqualTo("deliveryPerson", "delivery1");
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
                currentDeliAdapter.notifyDataSetChanged();
            }
        });
    }
}