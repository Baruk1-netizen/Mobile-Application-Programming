// Path: app/src/main/java/com/example/mobile_application_programming/cart/CartActivity.java

package com.example.mobile_application_programming.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobile_application_programming.R;
import com.google.android.material.button.MaterialButton;
import java.text.NumberFormat;
import java.util.Locale;

public class CartActivity extends AppCompatActivity implements CartManager.CartUpdateListener, CartAdapter.CartItemListener {
    private RecyclerView recyclerViewCart;
    private TextView emptyCartMessage;
    private TextView totalPrice;
    private MaterialButton checkoutButton;
    private CartManager cartManager;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Initialize Views
        initializeViews();
        
        // Setup Toolbar
        setupToolbar();
        
        // Initialize CartManager
        cartManager = CartManager.getInstance(this);
        cartManager.addListener(this);
        
        // Setup RecyclerView
        setupRecyclerView();
        
        // Setup Checkout Button
        setupCheckoutButton();
        
        // Update UI
        updateCartUI();
    }

    private void initializeViews() {
        recyclerViewCart = findViewById(R.id.recyclerViewCart);
        emptyCartMessage = findViewById(R.id.emptyCartMessage);
        totalPrice = findViewById(R.id.totalPrice);
        checkoutButton = findViewById(R.id.checkoutButton);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    private void setupRecyclerView() {
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new CartAdapter(this, cartManager.getCartItems(), this);
        recyclerViewCart.setAdapter(cartAdapter);
    }

    private void setupCheckoutButton() {
        checkoutButton.setOnClickListener(v -> {
            if (cartManager.getItemCount() > 0) {
                startCheckoutProcess();
            }
        });
    }

    private void startCheckoutProcess() {
        Intent intent = new Intent(this, CheckoutActivity.class);
        startActivity(intent);
    }

    private void updateCartUI() {
        if (cartManager.getItemCount() == 0) {
            recyclerViewCart.setVisibility(View.GONE);
            emptyCartMessage.setVisibility(View.VISIBLE);
            checkoutButton.setEnabled(false);
        } else {
            recyclerViewCart.setVisibility(View.VISIBLE);
            emptyCartMessage.setVisibility(View.GONE);
            checkoutButton.setEnabled(true);
        }

        // Update total price
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
        totalPrice.setText(currencyFormatter.format(cartManager.getTotal()));
        
        // Update adapter
        cartAdapter.updateItems(cartManager.getCartItems());
    }

    // Implementing CartManager.CartUpdateListener
    @Override
    public void onCartUpdated() {
        runOnUiThread(this::updateCartUI);
    }

    // Implementing CartAdapter.CartItemListener methods
    @Override
    public void onQuantityChanged(int productId, int newQuantity) {
        cartManager.updateItemQuantity(productId, newQuantity);
    }

    @Override
    public void onRemoveItem(int productId) {
        cartManager.removeItem(productId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        cartManager.removeListener(this);
        super.onDestroy();
    }
}