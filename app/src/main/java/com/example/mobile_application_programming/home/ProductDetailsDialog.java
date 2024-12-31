package com.example.mobile_application_programming.home;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.example.mobile_application_programming.R;
import com.example.mobile_application_programming.cart.CartManager;

public class ProductDetailsDialog extends Dialog {
    private final Product product;
    private final Context context;

    public ProductDetailsDialog(Context context, Product product) {
        super(context);
        this.context = context;
        this.product = product;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_product_details);

        ImageView productImage = findViewById(R.id.productDetailImage);
        TextView productName = findViewById(R.id.productDetailName);
        TextView productPrice = findViewById(R.id.productDetailPrice);
        TextView productDescription = findViewById(R.id.productDetailDescription);
        RatingBar productRating = findViewById(R.id.productDetailRating);
        Button addToCartButton = findViewById(R.id.productDetailAddToCart);
        Button closeButton = findViewById(R.id.productDetailClose);

        productImage.setImageResource(product.getImageResource());
        productName.setText(product.getName());
        productPrice.setText("$" + product.getPrice());
        productDescription.setText(product.getDescription());
        productRating.setRating(product.getRating());

        addToCartButton.setOnClickListener(v -> {
            CartManager.getInstance(context).addToCart(product);
            dismiss();
        });

        closeButton.setOnClickListener(v -> dismiss());
    }
}