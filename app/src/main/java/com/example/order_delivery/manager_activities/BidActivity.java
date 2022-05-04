package com.example.order_delivery.manager_activities;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.order_delivery.R;
import com.example.order_delivery.adapters.BidAdapter;
import com.example.order_delivery.delivery_activities.JustificationActivity;
import com.example.order_delivery.manager_activities.OrderList;
import com.example.order_delivery.model.Bid;
import com.example.order_delivery.model.CompleteOrder;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.parceler.Parcels;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

public class BidActivity extends AppCompatActivity {

    private RecyclerView rvBid;
    private List<Bid> all_item;
    private BidAdapter bidAdapter;
    public static double minBid = Double.MAX_VALUE;
    private String assignedTo;
    private String justification;
    private CompleteOrder completeOrder = new CompleteOrder();
    public static final int REQUEST_CODE = 1234;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid);
        rvBid = findViewById(R.id.rvBid);
        all_item = new ArrayList<>();
        bidAdapter = new BidAdapter(this, all_item);
        rvBid.setAdapter(bidAdapter);
        rvBid.setLayoutManager(new LinearLayoutManager(this));
        completeOrder = Parcels.unwrap(getIntent().getParcelableExtra("CompleteOrder"));
        rvBid.setHasFixedSize(true);
        queryPosts();
    }

    private void queryPosts() {
        ParseQuery<Bid> query = ParseQuery.getQuery("Bid");
        query.whereEqualTo("orderId", completeOrder.getOrderId());
        query.findInBackground(new FindCallback<Bid>() {
            @Override
            public void done(List<Bid> items, ParseException e) {
                if (e != null){
                    Log.e("Order activity", "Issue with getting posts", e);
                    return;
                }
                for (Bid item: items){
                    System.out.println(item.getBid());
                    Log.i("Order activity", "item:" + item.getBid());
                    if (item.getBid() < minBid){
                        minBid = item.getBid();
                    }

                }
                System.out.println("min bid" + minBid);
                all_item.addAll(items);
                bidAdapter.notifyDataSetChanged();
            }
        });
    }

    public void onAssignClick(View view) {
        if(all_item.size() == 0){
            Toast.makeText(this, "No one to assign", Toast.LENGTH_SHORT).show();
        }
        else{
            Bid temp = all_item.get(BidAdapter.positionClicked);
            if(temp.getBid() != minBid){
                Intent intent = new Intent(this, JustificationActivity.class);
                //ask for reason
                startActivityForResult(intent, REQUEST_CODE);
            }
            else{
                assignedTo = temp.getDeliveryPerson();
                System.out.println(assignedTo);
                //save deliver yin complete order
                completeOrder.setDeliveryPerson(assignedTo);
                completeOrder.saveInBackground();
                Intent intent = new Intent(this, OrderList.class);
                startActivity(intent);
                finish();
            }

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            //get data from intent (address)
            //update string
            Bid temp = all_item.get(BidAdapter.positionClicked);
            justification = data.getExtras().getString("justification");
            //save item here
            System.out.println(justification);
            assignedTo = temp.getDeliveryPerson();
            System.out.println(assignedTo);
            //save deliver yin complete order
            completeOrder.setDeliveryPerson(assignedTo);
            completeOrder.saveInBackground();
            Intent intent = new Intent(this, OrderList.class);
            startActivity(intent);
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}