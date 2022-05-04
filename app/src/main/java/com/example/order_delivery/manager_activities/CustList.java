package com.example.order_delivery.manager_activities;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order_delivery.R;
import com.example.order_delivery.adapters.CustListAdapter;
import com.example.order_delivery.model.sz_customer;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class CustList extends AppCompatActivity {

    private RecyclerView rvCustList;
    private CustListAdapter custListAdaptor;
    private List <sz_customer> test;
    private String TAG = "custlist";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_list);

        rvCustList = findViewById(R.id.rvCustList);

        test = new ArrayList<>();


        custListAdaptor = new CustListAdapter(this, test);
        rvCustList.setAdapter(custListAdaptor);
        rvCustList.setLayoutManager(new LinearLayoutManager(this));
        queryPosts();
    }
    private void queryPosts() {
        ParseQuery<sz_customer> query = ParseQuery.getQuery("sz_customer");
        query.findInBackground(new FindCallback<sz_customer>() {
            @Override
            public void done(List<sz_customer> items, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (sz_customer item: items){
                    Log.i(TAG, "item:" + item.getName());
                }
                test.addAll(items);
                custListAdaptor.notifyDataSetChanged();
            }
        });
    }
}
