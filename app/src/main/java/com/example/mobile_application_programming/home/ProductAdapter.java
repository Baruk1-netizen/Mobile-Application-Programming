
package com.example.mobile_application_programming.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_application_programming.R;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> products;
    private List<Product> filteredProducts;
    private final OnProductClickListener listener;

    public interface OnProductClickListener {
        void onAddToCart(Product product);
    }

    public ProductAdapter(List<Product> products, OnProductClickListener listener) {
        this.products = products;
        this.filteredProducts = new ArrayList<>(products);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = filteredProducts.get(position);
        holder.bind(product, listener);
    }

    @Override
    public int getItemCount() {
        return filteredProducts.size();
    }

    public void filter(String query) {
        filteredProducts.clear();
        if (query.isEmpty()) {
            filteredProducts.addAll(products);
        } else {
            String lowerCaseQuery = query.toLowerCase();
            for (Product product : products) {
                if (product.getName().toLowerCase().contains(lowerCaseQuery)) {
                    filteredProducts.add(product);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        RatingBar productRating;
        ImageButton addToCartButton;

        ProductViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productRating = itemView.findViewById(R.id.productRating);
            addToCartButton = itemView.findViewById(R.id.addToCartButton);
        }

        void bind(Product product, OnProductClickListener listener) {
            productImage.setImageResource(product.getImageResource());
            productName.setText(product.getName());
            productPrice.setText("$" + product.getPrice());
            productRating.setRating(product.getRating());
            
            addToCartButton.setOnClickListener(v -> listener.onAddToCart(product));
        }
    }
}