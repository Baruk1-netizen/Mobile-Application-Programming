package com.example.mobile_application_programming.home;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mobile_application_programming.R;
import com.example.mobile_application_programming.cart.CartManager;
import com.google.android.material.button.MaterialButton;

public class ProductDetailsDialog extends Dialog {
    private final Product product;
    private final Context context;

    public ProductDetailsDialog(@NonNull Context context, Product product) {
        super(context);
        this.context = context;
        this.product = product;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_product_details);

        // Set dialog width to match parent with margins
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, 
                             ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // Initialize views
        ImageView productImage = findViewById(R.id.productImage);
        TextView productName = findViewById(R.id.productName);
        TextView productPrice = findViewById(R.id.productPrice);
        TextView productDescription = findViewById(R.id.productDescription);
        RatingBar productRating = findViewById(R.id.productRating);
        MaterialButton addToCartButton = findViewById(R.id.addToCartButton);
        MaterialButton buyNowButton = findViewById(R.id.buyNowButton);

        // Set product details
        productImage.setImageResource(product.getImageResourceId());
        productName.setText(product.getName());
        productPrice.setText("$" + product.getPrice());
        productRating.setRating(product.getRating());
        
        // Set sample description (you should add this to your Product class)
        String description = "Experience premium quality with the " + product.getName() + 
                           ". This product offers excellent value for money with its " +
                           "superior features and elegant design.";
        productDescription.setText(description);

        // Button click listeners
        addToCartButton.setOnClickListener(v -> {
            CartManager.getInstance(context).addToCart(product);
            Toast.makeText(context, product.getName() + " added to cart", 
                         Toast.LENGTH_SHORT).show();
            dismiss();
        });

        buyNowButton.setOnClickListener(v -> {
            Toast.makeText(context, "Buy Now feature coming soon!", 
                         Toast.LENGTH_SHORT).show();
        });
    }
}