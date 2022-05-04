package com.example.order_delivery.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.order_delivery.customer_activities.CommentActivity;
import com.example.order_delivery.customer_activities.DetailActivity;
import com.example.order_delivery.R;
import com.example.order_delivery.model.sz_item_cust;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.List;

public class sz_item_custAdapter extends RecyclerView.Adapter<sz_item_custAdapter.ViewHolder> {
    private Context context;
    private List<sz_item_cust> item_cust;

    public sz_item_custAdapter(Context context, List<sz_item_cust> item_cust){
        this.context = context;
        this.item_cust = item_cust;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        sz_item_cust item = item_cust.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return item_cust.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivItem;
        private TextView tvItemName;
        private TextView tvDescription;
        private TextView tvPrice;
        private TextView tvRating;
        private Button btnComment;
        private RelativeLayout itemContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivItem = itemView.findViewById(R.id.ivItem);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvRating = itemView.findViewById(R.id.tvRating);
            btnComment = itemView.findViewById(R.id.btnItemComment);
            itemContainer = itemView.findViewById(R.id.itemContainer);

            btnComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, CommentActivity.class);
                    intent.putExtra("itemName", tvItemName.getText().toString());
                    context.startActivity(intent);
                }
            });
        }


        public void bind(sz_item_cust item) {
            tvItemName.setText(item.getItemName());
            tvDescription.setText(item.getItemDescription());
            tvPrice.setText("$" + item.getItemPrice());
            tvRating.setText(item.getItemRating() + "/5");
            ParseFile image = item.getItemImage();

            if (image != null){
                Glide.with(context).load(item.getItemImage().getUrl()).into(ivItem);
            }
            itemContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Item clicked in adapter", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("test", Parcels.wrap(item));
                    context.startActivity(i);
//                    System.out.println(getAdapterPosition());
//                    item.deleteInBackground(); //delete item on server
//                    item_cust.remove(getAdapterPosition()); //delete locally on recycler list

//                    notifyDataSetChanged(); //update recycler view
                }
            });
        }
    }
}
