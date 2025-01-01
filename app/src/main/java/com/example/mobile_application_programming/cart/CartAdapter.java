// Path: app/src/main/java/com/example/mobile_application_programming/cart/CartAdapter.java

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
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private List<CartItem> cartItems;
    private CartItemListener listener;

    public interface CartItemListener {
        void onQuantityChanged(int productId, int newQuantity);
        void onRemoveItem(int productId);
    }

    public CartAdapter(Context context, List<CartItem> cartItems, CartItemListener listener) {
        this.context = context;
        this.cartItems = cartItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        
        // Set item details
        holder.productName.setText(item.getName());
        holder.productPrice.setText("$" + item.getPrice());
        holder.quantityText.setText(String.valueOf(item.getQuantity()));
        
        // Set image from resource
        holder.productImage.setImageResource(item.getImageResource());

        // Setup quantity controls
        holder.decrementButton.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                listener.onQuantityChanged(item.getId(), item.getQuantity() - 1);
            } else {
                listener.onRemoveItem(item.getId());
            }
        });

        holder.incrementButton.setOnClickListener(v -> {
            listener.onQuantityChanged(item.getId(), item.getQuantity() + 1);
        });

        // Long press to remove item
        holder.itemView.setOnLongClickListener(v -> {
            listener.onRemoveItem(item.getId());
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public void updateItems(List<CartItem> newItems) {
        this.cartItems = newItems;
        notifyDataSetChanged();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        TextView quantityText;
        ImageButton decrementButton;
        ImageButton incrementButton;

        CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            quantityText = itemView.findViewById(R.id.quantityText);
            decrementButton = itemView.findViewById(R.id.decrementButton);
            incrementButton = itemView.findViewById(R.id.incrementButton);
        }
    }
}