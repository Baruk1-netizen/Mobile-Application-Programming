package com.example.mobile_application_programming.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobile_application_programming.R;
import com.example.mobile_application_programming.home.Product;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<Product> cartItems;
    private final Context context;
    private final CartItemListener listener;

    public interface CartItemListener {
        void onQuantityChanged(int position, int quantity);
        void onRemoveItem(int position);
    }

    public CartAdapter(Context context, List<Product> cartItems, CartItemListener listener) {
        this.context = context;
        this.cartItems = cartItems;
        this.listener = listener;
    }

    public void updateItems(List<Product> newItems) {
        this.cartItems = newItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = cartItems.get(position);
        
        holder.productImage.setImageResource(product.getImageResource());
        holder.productName.setText(product.getName());
        holder.productPrice.setText("$" + product.getPrice());
        holder.quantityText.setText(String.valueOf(product.getQuantity()));

        holder.decreaseButton.setOnClickListener(v -> {
            int newQuantity = product.getQuantity() - 1;
            if (newQuantity >= 1) {
                listener.onQuantityChanged(position, newQuantity);
            }
        });

        holder.increaseButton.setOnClickListener(v -> {
            int newQuantity = product.getQuantity() + 1;
            if (newQuantity <= 10) {
                listener.onQuantityChanged(position, newQuantity);
            }
        });

        holder.removeButton.setOnClickListener(v -> 
            listener.onRemoveItem(position)
        );
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        TextView quantityText;
        ImageButton decreaseButton;
        ImageButton increaseButton;
        ImageButton removeButton;

        CartViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.cartItemImage);
            productName = itemView.findViewById(R.id.cartItemName);
            productPrice = itemView.findViewById(R.id.cartItemPrice);
            quantityText = itemView.findViewById(R.id.quantityText);
            decreaseButton = itemView.findViewById(R.id.decreaseButton);
            increaseButton = itemView.findViewById(R.id.increaseButton);
            removeButton = itemView.findViewById(R.id.removeButton);
        }
    }
}