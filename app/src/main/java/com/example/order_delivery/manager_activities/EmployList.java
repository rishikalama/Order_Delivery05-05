package com.example.order_delivery.manager_activities;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order_delivery.R;
import com.example.order_delivery.adapters.EmployListAdapter;
import com.example.order_delivery.model.Employee;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class EmployList extends AppCompatActivity {
    private RecyclerView rvEmployList;
    private EmployListAdapter employListAdaptor;
    private List<Employee> test;

    private String TAG = "EmployList";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employ_list);

        rvEmployList = findViewById(R.id.rvEmployList);

        test = new ArrayList<>();

        employListAdaptor = new EmployListAdapter(this, test);
        rvEmployList.setAdapter(employListAdaptor);
        rvEmployList.setLayoutManager(new LinearLayoutManager(this));
        queryPosts();
    }


    private void queryPosts() {
        ParseQuery<Employee> query = ParseQuery.getQuery("Employee");
        query.findInBackground(new FindCallback<Employee>() {
            @Override
            public void done(List<Employee> items, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (Employee item: items){
                    Log.i(TAG, "item:" + item.getName());
                }
                test.addAll(items);
                employListAdaptor.notifyDataSetChanged();
            }
        });
    }
}
