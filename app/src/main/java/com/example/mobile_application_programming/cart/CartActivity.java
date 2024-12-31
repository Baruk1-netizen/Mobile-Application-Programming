package com.example.mobile_application_programming.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobile_application_programming.R;
import com.example.mobile_application_programming.navigation.BottomNavigationBar;
import java.text.NumberFormat;
import java.util.Locale;

public class CartActivity extends AppCompatActivity implements BottomNavigationBar.NavigationListener {
    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private TextView totalPriceText;
    private TextView emptyCartText;
    private Button checkoutButton;
    private CartManager cartManager;
    private BottomNavigationBar navigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Initialize views
        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        totalPriceText = findViewById(R.id.totalPriceText);
        emptyCartText = findViewById(R.id.emptyCartText);
        checkoutButton = findViewById(R.id.checkoutButton);
        navigationBar = findViewById(R.id.bottomNavigation);

        // Setup navigation
        navigationBar.setNavigationListener(this);
        navigationBar.setSelectedTab(2);

        // Initialize cart manager
        cartManager = CartManager.getInstance(this);

        // Setup RecyclerView
        setupRecyclerView();

        // Setup checkout button
        checkoutButton.setOnClickListener(v -> {
            if (cartManager.getCartSize() > 0) {
                startActivity(new Intent(this, CheckoutActivity.class));
            }
        });

        updateUI();
    }

    private void setupRecyclerView() {
        cartAdapter = new CartAdapter(this, cartManager.getCartItems(), new CartAdapter.CartItemListener() {
            @Override
            public void onQuantityChanged(int position, int quantity) {
                cartManager.updateQuantity(cartManager.getCartItems().get(position), quantity);
                updateUI();
            }

            @Override
            public void onRemoveItem(int position) {
                cartManager.removeFromCart(cartManager.getCartItems().get(position));
                updateUI();
            }
        });

        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);
    }

    private void updateUI() {
        if (cartManager.getCartSize() > 0) {
            emptyCartText.setVisibility(View.GONE);
            cartRecyclerView.setVisibility(View.VISIBLE);
            checkoutButton.setEnabled(true);
            
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
            totalPriceText.setText("Total: " + currencyFormat.format(cartManager.getCartTotal()));
        } else {
            emptyCartText.setVisibility(View.VISIBLE);
            cartRecyclerView.setVisibility(View.GONE);
            checkoutButton.setEnabled(false);
            totalPriceText.setText("Total: $0.00");
        }
        cartAdapter.updateItems(cartManager.getCartItems());
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onTabSelected(int position) {
        if (position != 2) {
            finish();
        }
    }
}