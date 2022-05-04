package com.example.order_delivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.order_delivery.customer_activities.fragments.CheckoutFragment;
import com.example.order_delivery.customer_activities.fragments.CustProfileFragment;
import com.example.order_delivery.customer_activities.fragments.SelectMenuFragment;
import com.example.order_delivery.local_model.CurrentUserInfo;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MenuActivity extends AppCompatActivity {
//    private RecyclerView rvMenu;
//    private sz_item_custAdapter adapter;
//    private List<sz_item_cust> all_item;
//    private List<sz_item_cust> temp_item;
//    private CountDownTimer countDownTimer;
//    public static final String TAG = "MenuActivity";
    private BottomNavigationView menuBottomNavigationView;

    final FragmentManager fragmentManager = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        System.out.println(CurrentUserInfo.currentUserName);


        menuBottomNavigationView = findViewById(R.id.menu_bottom_navigation);
        menuBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_menu:
                        //update fragments
                        Toast.makeText(MenuActivity.this, "menu clicked", Toast.LENGTH_SHORT).show();
                        fragment = new SelectMenuFragment();
                        break;
                    case R.id.action_profile:
                        Toast.makeText(MenuActivity.this, "profile clicked", Toast.LENGTH_SHORT).show();
                        fragment = new CustProfileFragment();
                        break;
                    case R.id.action_report:
                        //need to chagne this
                        Toast.makeText(MenuActivity.this, "report clicked", Toast.LENGTH_SHORT).show();
                        fragment = new ComplaintFragment();
                        break;
                    case R.id.action_bell:
                        Toast.makeText(MenuActivity.this, "bell clicked", Toast.LENGTH_SHORT).show();
                        fragment = new NotificationFragment();
                        break;
                    case R.id.action_cart:
                        Toast.makeText(MenuActivity.this, "cart clicked", Toast.LENGTH_SHORT).show();
                        fragment = new CheckoutFragment();
                        break;
                    default:
                        fragment = new SelectMenuFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flMenuContainer, fragment).commit();
                return true;
            }
        });
        //set default
        menuBottomNavigationView.setSelectedItemId(R.id.action_menu);
    }
//        rvMenu = findViewById(R.id.rvMenu);
//        all_item = new ArrayList<>();
//        temp_item =  new ArrayList<>();
//        queryPosts();
//        adapter = new sz_item_custAdapter(this, all_item);
//        rvMenu.setAdapter(adapter);
//        rvMenu.setLayoutManager(new LinearLayoutManager(this));
////        refreshView();
//    }

//    private void queryPosts() {
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
//                all_item.addAll(items);
//                adapter.notifyDataSetChanged();
//            }
//        });
//    }
//
//    public void onGoToCheckOutClick(View view) {
//        Intent i = new Intent(this, CheckOut.class);
//        startActivity(i);
//    }
//
//    public void onProfileClick(View view) {
//        Intent i = new Intent(this, ProfileActivity.class);
//        startActivity(i);
//    }

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