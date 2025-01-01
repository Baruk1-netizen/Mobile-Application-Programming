package com.example.mobile_application_programming.cart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View; // Add this line
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.text.TextUtils;

import java.text.NumberFormat;



import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_application_programming.MainActivity;
import com.example.mobile_application_programming.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class CheckoutActivity extends AppCompatActivity {
    private TextInputEditText fullNameInput, addressInput, cityInput, zipCodeInput;
    private TextInputEditText cardNumberInput, expiryInput, cvvInput, phoneNumberInput;
    private RadioGroup paymentMethodGroup;
    private LinearLayout creditCardLayout, mpesaLayout;
    private TextView subtotalText, shippingText, totalText;
    private MaterialButton placeOrderButton;
    private CartManager cartManager;
    private double subtotal, shipping, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        
        initViews();
        setupToolbar();
        setupPaymentMethodListeners();
        loadCartSummary();
        setupPlaceOrderButton();
    }

    private void initViews() {
        // Initialize input fields
        fullNameInput = findViewById(R.id.fullNameInput);
        addressInput = findViewById(R.id.addressInput);
        cityInput = findViewById(R.id.cityInput);
        zipCodeInput = findViewById(R.id.zipCodeInput);
        
        // Payment method views
        paymentMethodGroup = findViewById(R.id.paymentMethodGroup);
        creditCardLayout = findViewById(R.id.creditCardLayout);
        mpesaLayout = findViewById(R.id.mpesaLayout);
        
        // Credit card inputs
        cardNumberInput = findViewById(R.id.cardNumberInput);
        expiryInput = findViewById(R.id.expiryInput);
        cvvInput = findViewById(R.id.cvvInput);
        
        // M-PESA input
        phoneNumberInput = findViewById(R.id.phoneNumberInput);
        
        // Summary views
        subtotalText = findViewById(R.id.subtotalText);
        shippingText = findViewById(R.id.shippingText);
        totalText = findViewById(R.id.totalText);
        
        placeOrderButton = findViewById(R.id.placeOrderButton);
        
        cartManager = CartManager.getInstance(this);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    private void setupPaymentMethodListeners() {
        paymentMethodGroup.setOnCheckedChangeListener((group, checkedId) -> {
            creditCardLayout.setVisibility(View.GONE);
            mpesaLayout.setVisibility(View.GONE);
            
            if (checkedId == R.id.creditCardRadio) {
                creditCardLayout.setVisibility(View.VISIBLE);
            } else if (checkedId == R.id.mpesaRadio) {
                mpesaLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    private void loadCartSummary() {
        subtotal = cartManager.getTotal();
        shipping = calculateShipping();
        total = subtotal + shipping;
        
        subtotalText.setText(String.format("$%.2f", subtotal));
        shippingText.setText(String.format("$%.2f", shipping));
        totalText.setText(String.format("$%.2f", total));
    }

    private double calculateShipping() {
        // Implement your shipping calculation logic
        return 5.99; // Example fixed shipping cost
    }

    private void setupPlaceOrderButton() {
        placeOrderButton.setOnClickListener(v -> {
            if (validateInputs()) {
                showOrderProcessingDialog();
                processOrder();
            }
        });
    }

    private boolean validateInputs() {
        // Validate shipping address
        if (TextUtils.isEmpty(fullNameInput.getText()) ||
            TextUtils.isEmpty(addressInput.getText()) ||
            TextUtils.isEmpty(cityInput.getText()) ||
            TextUtils.isEmpty(zipCodeInput.getText())) {
            Toast.makeText(this, "Please fill in all shipping details", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate payment method
        int selectedPaymentMethod = paymentMethodGroup.getCheckedRadioButtonId();
        if (selectedPaymentMethod == -1) {
            Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate payment details
        if (selectedPaymentMethod == R.id.creditCardRadio) {
            if (TextUtils.isEmpty(cardNumberInput.getText()) ||
                TextUtils.isEmpty(expiryInput.getText()) ||
                TextUtils.isEmpty(cvvInput.getText())) {
                Toast.makeText(this, "Please fill in all card details", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else if (selectedPaymentMethod == R.id.mpesaRadio) {
            if (TextUtils.isEmpty(phoneNumberInput.getText())) {
                Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }

    private void showOrderProcessingDialog() {
        OrderProcessingDialog processingDialog = new OrderProcessingDialog();
        processingDialog.show(getSupportFragmentManager(), "processing_dialog");
    }

    private void processOrder() {
        // Simulate order processing
        new Handler().postDelayed(() -> {
            // Close processing dialog
            OrderProcessingDialog processingDialog = 
                (OrderProcessingDialog) getSupportFragmentManager()
                    .findFragmentByTag("processing_dialog");
            if (processingDialog != null) {
                processingDialog.dismiss();
            }

            // Clear cart
            cartManager.clearCart();

            // Show confirmation dialog
            showOrderConfirmationDialog();
        }, 2000);
    }

    private void showOrderConfirmationDialog() {
        OrderConfirmationDialog confirmationDialog = new OrderConfirmationDialog();
        confirmationDialog.show(getSupportFragmentManager(), "confirmation_dialog");
        confirmationDialog.setOnDismissListener(() -> {
            // Navigate back to main activity
            Intent intent = new Intent(CheckoutActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
