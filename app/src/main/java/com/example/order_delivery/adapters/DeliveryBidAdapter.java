package com.example.order_delivery.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order_delivery.R;
import com.example.order_delivery.model.Bid;
import com.example.order_delivery.model.CompleteOrder;

import java.util.List;

public class DeliveryBidAdapter extends RecyclerView.Adapter<DeliveryBidAdapter.ViewHolder> {
    private Context context;
    private List<CompleteOrder> item_cust;

    public DeliveryBidAdapter(Context context, List<CompleteOrder> item_cust){
        this.context = context;
        this.item_cust = item_cust;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bid_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryBidAdapter.ViewHolder holder, int position) {
        CompleteOrder item = item_cust.get(position);
        holder.bind(item, position);
    }

    @Override
    public int getItemCount() {
        return item_cust.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvBidItemInfo;
        private TextView tvBidItemIndex;
        private EditText etBidAmount;
        private Button btnSubmitBid;
        private Bid saveBid = new Bid();
        private String itemInfo = "";


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBidItemInfo = itemView.findViewById(R.id.tvBidItemInfo);
            tvBidItemIndex = itemView.findViewById(R.id.tvBidItemIndex);
            etBidAmount = itemView.findViewById(R.id.etBidAmount);
            btnSubmitBid = itemView.findViewById(R.id.btnSubmitBid);

        }


        public void bind(CompleteOrder item, int position) {
            tvBidItemIndex.setText("Order Number: " + (position + 1));
            itemInfo = String.format("To customer: %s\nTo Address: %s\nItems:\n%s\n Total Cost: %.2f", item.getName(), item.getAddress(), item.getFormattedList(), item.getTotal());
            tvBidItemInfo.setText(itemInfo);
            btnSubmitBid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    double bidAmount = Double.parseDouble(etBidAmount.getText().toString());
                    if(bidAmount == 0){
                        Toast.makeText(context, "Bid needs to be greater than 0", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(context, "Bidding " + etBidAmount.getText().toString(), Toast.LENGTH_SHORT).show();
                        //check if it has been assigned
                        //this is important
                        //as current code does not check for it
                        saveBid.setBid(bidAmount);
                        saveBid.setOrderId(item.getOrderId());
                        saveBid.setDeliveryPerson("delivery test");
                        saveBid.saveInBackground();
                    }
                }
            });
//            tvCustListName.setText("Name: " + item.getName() + "         User name: " +item.getUserName());
//            tvCustListId.setText("id" + item.getUserId());
//            tvCustListVip.setText("vip" + item.getVip());
//            tvCustListVer.setText("verification" + item.getVerified());
//            tvCustListBlacklist.setText("blacklist" + item.getBlackList());
//            tvCustBalance.setText("Balance" + item.getBalance());
//            tvCustWarning.setText("warning" + item.getWarning());

        }
    }
}
