package com.example.order_delivery.customer_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.order_delivery.R;
import com.example.order_delivery.local_model.CurrentUserInfo;
import com.example.order_delivery.model.Comments;

public class AddCommentActivity extends AppCompatActivity {
    private String itemName;
    private RatingBar rbRating;
    private EditText etComments;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        rbRating = findViewById(R.id.rbRating);
        etComments = findViewById(R.id.etComments);
        intent = getIntent();
        itemName = intent.getExtras().getString("itemName");

    }


    public void onSubmitCommentClick(View view) {
        Comments comment = new Comments();
        comment.setItemName(itemName);
        comment.setUsername(CurrentUserInfo.currentUserName);
        if(!CurrentUserInfo.currentUserVip){
            //for nonvip
            comment.setStatus("register");
            comment.setWeight(1);
        }
        else{
            //for vip
            comment.setStatus("vip");
            comment.setWeight(2);
        }

        if(rbRating.getRating() != 0){
            comment.setRating((int) rbRating.getRating());
        }
        else{
            //rating == 0
            Toast.makeText(this, "Please leave a rating", Toast.LENGTH_SHORT).show();
        }

        if(etComments.getText().toString() != null){
            comment.setComment(etComments.getText().toString());
        }
        else{
            Toast.makeText(this, "Please leave a comment", Toast.LENGTH_SHORT).show();
        }

        if(etComments.getText().toString() != null && rbRating.getRating() != 0){
            comment.saveInBackground();
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void onAddCommentBackClick(View view) {
        finish();
    }
}