package com.example.order_delivery.customer_activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.order_delivery.R;
import com.example.order_delivery.adapters.CommentsAdapter;
import com.example.order_delivery.customer_activities.AddCommentActivity;
import com.example.order_delivery.model.Comments;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {

    private RecyclerView rvComments;
    private String itemName;
    private List<Comments> all_item;
    private CommentsAdapter commentsAdapter;
    public static final int REQUEST_CODE = 21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        all_item = new ArrayList<>();
        Intent intent = getIntent();
        itemName = intent.getExtras().getString("itemName");
        rvComments = findViewById(R.id.rvComments);
        queryPosts();
        commentsAdapter = new CommentsAdapter(this, all_item);
        rvComments.setHasFixedSize(true);
        rvComments.setAdapter(commentsAdapter);
        rvComments.setLayoutManager(new LinearLayoutManager(this));
    }

    //here save comments to back4app
    public void onAddCommentClick(View view) {
        Intent intent = new Intent(this, AddCommentActivity.class);
        intent.putExtra("itemName", itemName);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            all_item.clear();
            queryPosts();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void queryPosts() {
        ParseQuery<Comments> query = ParseQuery.getQuery(Comments.class);
        query.whereEqualTo("itemName", itemName);
        query.findInBackground(new FindCallback<Comments>() {
            @Override
            public void done(List<Comments> items, ParseException e) {
                if (e != null){
                    Log.e("Comments Activity", "Issue with getting posts", e);
                    return;
                }
                for (Comments item: items){
                    Log.i("Comments Activity", "item:" + item.getItemName());
                }
                all_item.addAll(items);
                commentsAdapter.notifyDataSetChanged();
            }
        });
    }

    public void onActivityCommentBackClick(View view) {finish();
    }
}