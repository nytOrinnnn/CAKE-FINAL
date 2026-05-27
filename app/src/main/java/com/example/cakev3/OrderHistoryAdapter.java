package com.example.cakev3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderViewHolder> {

    private List<CakeOrder> orders;

    public OrderHistoryAdapter(List<CakeOrder> orders) {
        this.orders = orders;
    }

    public void setOrders(List<CakeOrder> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        CakeOrder order = orders.get(position);
        holder.tvOrderNum.setText("#" + order.id);
        holder.tvFlavor.setText("Flavor: " + order.flavor);
        holder.tvSize.setText("Size: " + order.size);
        holder.tvIcing.setText("Icing: " + order.icing);
        holder.tvTopping.setText("Topping: " + order.topping);
        holder.tvTotal.setText("₱" + order.totalPrice);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy  hh:mm a", Locale.getDefault());
        holder.tvDate.setText(sdf.format(new Date(order.timestamp)));
    }

    @Override
    public int getItemCount() {
        return orders == null ? 0 : orders.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderNum, tvFlavor, tvSize, tvIcing, tvTopping, tvTotal, tvDate;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderNum = itemView.findViewById(R.id.tvOrderNum);
            tvFlavor = itemView.findViewById(R.id.tvFlavor);
            tvSize = itemView.findViewById(R.id.tvSize);
            tvIcing = itemView.findViewById(R.id.tvIcing);
            tvTopping = itemView.findViewById(R.id.tvTopping);
            tvTotal = itemView.findViewById(R.id.tvTotal);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}
