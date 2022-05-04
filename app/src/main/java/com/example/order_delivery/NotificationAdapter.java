package com.example.order_delivery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order_delivery.adapters.BidAdapter;
import com.example.order_delivery.model.Bid;
import com.example.order_delivery.model.Notification;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private Context context;
    private List<Notification> item_order;
    public NotificationAdapter(Context context, List<Notification> item_order){
        this.context = context;
        this.item_order = item_order;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notification_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notification item = item_order.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return item_order.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNotifSubject;
        private TextView tvNotifFrom;
        private TextView tvNotifMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNotifFrom = itemView.findViewById(R.id.tvNotifFrom);
            tvNotifSubject = itemView.findViewById(R.id.tvNotifSubject);
            tvNotifMessage = itemView.findViewById(R.id.tvNotifMessage);
        }



        public void bind(Notification item) {
            tvNotifFrom.setText("From: " + item.getFromUser());
            tvNotifMessage.setText("Message: " + item.getMessage());
            tvNotifSubject.setText("Subject: " + item.getSubject());
        }
    }
}