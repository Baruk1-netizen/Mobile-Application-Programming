package com.example.mobile_application_programming.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_application_programming.R;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView productsRecyclerView;
    private ProductAdapter productAdapter;
    private EditText searchBar;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize views
        productsRecyclerView = findViewById(R.id.productsRecyclerView);
        searchBar = findViewById(R.id.searchBar);

        // Setup RecyclerView
        productsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(productList, this::onAddToCart);
        productsRecyclerView.setAdapter(productAdapter);

        // Load products first
        loadProducts();

        // Setup search functionality after products are loaded
        setupSearch();
    }

    private void loadProducts() {
        // Clear existing products
        productList.clear();
        
        // Add all products
        productList.add(new Product(1, "Smart Watch Pro", "199.99", 4.5f, R.drawable.watch));
        productList.add(new Product(2, "Classic T-Shirt", "29.99", 4.0f, R.drawable.tshirt));
        productList.add(new Product(3, "Running Shoes", "89.99", 4.8f, R.drawable.shoes));
        productList.add(new Product(4, "Leather Backpack", "79.99", 4.2f, R.drawable.backpack));
        productList.add(new Product(5, "Wireless Earbuds", "129.99", 4.7f, R.drawable.earbuds));
        productList.add(new Product(6, "Denim Jeans", "59.99", 4.3f, R.drawable.jeans));
        productList.add(new Product(7, "Sunglasses", "49.99", 4.1f, R.drawable.sunglasses));
        productList.add(new Product(8, "Sports Water Bottle", "19.99", 4.4f, R.drawable.bottle));
        productList.add(new Product(9, "Fitness Tracker", "69.99", 4.6f, R.drawable.tracker));
        productList.add(new Product(10, "Laptop Sleeve", "39.99", 4.2f, R.drawable.sleeve));
        productList.add(new Product(11, "Phone Case", "24.99", 4.0f, R.drawable.phonecase));
        productList.add(new Product(12, "Wireless Mouse", "34.99", 4.3f, R.drawable.mouse));

        // Update adapter with new products and show all
        productAdapter.updateProducts(productList);
    }

    private void setupSearch() {
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                productAdapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void onAddToCart(Product product) {
        Toast.makeText(this, product.getName() + " added to cart", Toast.LENGTH_SHORT).show();
    }
}