package com.example.order_delivery.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order_delivery.R;
import com.example.order_delivery.model.Bid;

import java.util.List;

public class BidAdapter extends RecyclerView.Adapter<BidAdapter.ViewHolder> {
    private Context context;
    private List<Bid> item_order;
    public static int positionClicked;
    public BidAdapter(Context context, List<Bid> item_order){
        this.context = context;
        this.item_order = item_order;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bid_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bid item = item_order.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return item_order.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvBidderName;
        private TextView tvBidderPrice;
        private LinearLayout BidItemContainer;
        private String assignedTo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBidderName = itemView.findViewById(R.id.tvBidderName);
            tvBidderPrice = itemView.findViewById(R.id.tvBidderPrice);
            BidItemContainer = itemView.findViewById(R.id.BidItemContainer);
        }



        public void bind(Bid item) {
            tvBidderName.setText(item.getDeliveryPerson());
            tvBidderPrice.setText(String.valueOf(item.getBid()));

            BidItemContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    positionClicked = getAdapterPosition();
                }
            });
        }
    }
}
