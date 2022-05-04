package com.example.order_delivery.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.order_delivery.R;
import com.example.order_delivery.local_model.CartItem;

import java.util.List;

public class checkOutAdapter extends RecyclerView.Adapter<checkOutAdapter.ViewHolder> {
    private Context context;
    private List<CartItem> cartItems;

    public checkOutAdapter(Context context, List<CartItem> cartItems){
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivCartItem;
        private TextView tvCartName;
        private TextView tvCartPrice;
        private TextView tvCartQuantity;
        private RelativeLayout CartContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCartItem = itemView.findViewById(R.id.ivCartItem);
            tvCartName = itemView.findViewById(R.id.tvCartName);
            tvCartPrice = itemView.findViewById(R.id.tvCartPrice);
            tvCartQuantity = itemView.findViewById(R.id.tvCartQuantity);
        }

        public void bind(CartItem item) {
            tvCartName.setText(item.getName());
            tvCartPrice.setText("$ " + item.getPrice());
            tvCartQuantity.setText("Quantity: " + item.getQuantity());
            Glide.with(context).load(item.getImageUrl()).into(ivCartItem);
        }
    }

}
