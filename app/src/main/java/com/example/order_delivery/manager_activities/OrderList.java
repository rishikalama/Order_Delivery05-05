package com.example.order_delivery.manager_activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order_delivery.R;
import com.example.order_delivery.adapters.OrderListAdapter;
import com.example.order_delivery.model.CompleteOrder;
import com.google.android.material.textfield.TextInputEditText;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class OrderList extends AppCompatActivity {
    private RecyclerView rvOrderList;
    private List<CompleteOrder> all_item;
    private OrderListAdapter orderListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        rvOrderList = findViewById(R.id.rvOrderList);
        all_item = new ArrayList<>();
        orderListAdapter = new OrderListAdapter(this, all_item);
        rvOrderList.setAdapter(orderListAdapter);
        rvOrderList.setLayoutManager(new LinearLayoutManager(this));
        queryPosts();
    }

    private void queryPosts() {
        ParseQuery<CompleteOrder> query = ParseQuery.getQuery("CompleteOrder");
        query.findInBackground(new FindCallback<CompleteOrder>() {
            @Override
            public void done(List<CompleteOrder> items, ParseException e) {
                if (e != null){
                    Log.e("Order activity", "Issue with getting posts", e);
                    return;
                }
                for (CompleteOrder item: items){
                    Log.i("Order activity", "item:" + item.getOrderId());
                }
                all_item.addAll(items);
                orderListAdapter.notifyDataSetChanged();
            }
        });
    }
}
