package com.example.order_delivery.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order_delivery.R;
import com.example.order_delivery.model.Employee;

import java.util.List;

public class EmployListAdapter extends RecyclerView.Adapter<EmployListAdapter.ViewHolder> {
    private Context context;
    private List<Employee> item_employ;

    public EmployListAdapter(Context context, List<Employee> item_cust){
        this.context = context;
        this.item_employ = item_cust;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.employee_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Employee item = item_employ.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return item_employ.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvEmployListName;
        private TextView tvEmployListPosition;
        private TextView tvEmployListSalary;
        private TextView tvEmployListRating;
        private TextView tvEmployListId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmployListName = itemView.findViewById(R.id.tvEmployListName);
            tvEmployListPosition = itemView.findViewById(R.id.tvEmployListPosition);
            tvEmployListSalary = itemView.findViewById(R.id.tvEmployListSalary);
            tvEmployListRating = itemView.findViewById(R.id.tvEmployRating);
            tvEmployListId = itemView.findViewById(R.id.tvEmployListId);
        }


        public void bind(Employee item) {
            tvEmployListName.setText(item.getName());
            tvEmployListPosition.setText("Position: " + item.getEmployTitle());
            tvEmployListSalary.setText("Salary: " + item.getSalary());
            tvEmployListRating.setText("Rating: " + item.getRating());
            tvEmployListId.setText("ID: " + item.getEmployId());

        }
    }
}