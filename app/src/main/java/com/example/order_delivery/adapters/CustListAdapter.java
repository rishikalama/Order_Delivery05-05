package com.example.order_delivery.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.order_delivery.R;
import com.example.order_delivery.model.sz_customer;
import com.parse.ParseFile;

import org.w3c.dom.Text;

import java.util.List;

public class CustListAdapter extends RecyclerView.Adapter<CustListAdapter.ViewHolder> {
    private Context context;
    private List<sz_customer> item_cust;

    public CustListAdapter(Context context, List<sz_customer> item_cust){
        this.context = context;
        this.item_cust = item_cust;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cust_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        sz_customer item = item_cust.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return item_cust.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvCustListName;
        private TextView tvCustListId;
        private TextView tvCustListVip;
        private TextView tvCustListVer;
        private TextView tvCustListBlacklist;
        private TextView tvCustWarning;
        private TextView tvCustBalance;
        private TextView tvCustActivate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCustListName = itemView.findViewById(R.id.tvCustListName);
            tvCustListId = itemView.findViewById(R.id.tvCustListId);
            tvCustListVip = itemView.findViewById(R.id.tvCustListVip);
            tvCustListVer = itemView.findViewById(R.id.tvCustListVer);
            tvCustListBlacklist = itemView.findViewById(R.id.tvCustListBlacklist);
            tvCustWarning = itemView.findViewById(R.id.tvCustWarning);
            tvCustBalance = itemView.findViewById(R.id.tvCustBalance);
            tvCustActivate = itemView.findViewById(R.id.tvCustActivate);
        }


        public void bind(sz_customer item) {
            tvCustListName.setText("Name: " + item.getName() + "         User name: " +item.getUserName());
            tvCustListId.setText("id" + item.getUserId());
            tvCustListVip.setText("vip" + item.getVip());
            tvCustListVer.setText("verification" + item.getVerified());
            tvCustListBlacklist.setText("blacklist" + item.getBlackList());
            tvCustBalance.setText("Balance" + item.getBalance());
            tvCustWarning.setText("warning" + item.getWarning());

        }
    }
}
