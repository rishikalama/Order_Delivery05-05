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
import androidx.recyclerview.widget.RecyclerView;

import com.example.order_delivery.R;
import com.example.order_delivery.manager_activities.BidActivity;
import com.example.order_delivery.model.CompleteOrder;

import org.parceler.Parcels;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {
    private Context context;
    private List<CompleteOrder> item_order;
    private final int REQUEST_CODE = 30;

    public OrderListAdapter(Context context, List<CompleteOrder> item_order){
        this.context = context;
        this.item_order = item_order;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CompleteOrder item = item_order.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return item_order.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvOrderCustName;
        private TextView tvOrderCustAddress;
        private TextView tvOrderList;
        private TextView tvOrderPrice;
        private TextView tvAssignedTo;
        private LinearLayout OrderItemContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderCustName = itemView.findViewById(R.id.tvOrderCustName);
            tvOrderCustAddress = itemView.findViewById(R.id.tvOrderCustAddress);
            tvOrderList = itemView.findViewById(R.id.tvOrderList);
            tvOrderPrice = itemView.findViewById(R.id.tvOrderPrice);
            tvAssignedTo = itemView.findViewById(R.id.tvAssignTo);
            OrderItemContainer = itemView.findViewById(R.id.OrderItemContainer);
        }

        public void bind(CompleteOrder item) {
            tvOrderCustName.setText(item.getName());
            tvOrderCustAddress.setText("Address: " + item.getAddress());
            tvOrderPrice.setText("Total Cost : " + item.getTotal());
            tvAssignedTo.setText("Assigned to: " + item.getDeliveryPerson() + "\n" + "Justification: " + item.getDeliverJust());

            String[] test = item.getList().split(",");
            String order = "";
            for (String str : test) {
                String[] temp = str.split(":");
                order += "Item: " + temp[0] + " x" + temp[1] + "\n";
            }
            tvOrderList.setText(order);
            OrderItemContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "item clicks: " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    if(item.getDeliveryPerson().equals("not assigned")){
                        Intent intent = new Intent(context, BidActivity.class);
                        intent.putExtra("CompleteOrder", Parcels.wrap(item));
                        context.startActivity(intent);
                        ((Activity) context).finish();
                    }
                }
            });
        }
    }
}