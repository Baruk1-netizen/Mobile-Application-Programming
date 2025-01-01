package com.example.mobile_application_programming.cart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mobile_application_programming.MainActivity;
import com.example.mobile_application_programming.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.NumberFormat;

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
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    private void setupPaymentMethodListeners() {
        paymentMethodGroup.setOnCheckedChangeListener((group, checkedId) -> {
            creditCardLayout.setVisibility(View.GONE);
            mpesaLayout.setVisibility(View.GONE);
            
            if (checkedId == R.id.creditCardRadio) {
                creditCardLayout.setVisibility(View.VISIBLE);
                creditCardLayout.setAlpha(0f);
                creditCardLayout.animate().alpha(1f).setDuration(200);
            } else if (checkedId == R.id.mpesaRadio) {
                mpesaLayout.setVisibility(View.VISIBLE);
                mpesaLayout.setAlpha(0f);
                mpesaLayout.animate().alpha(1f).setDuration(200);
            }
        });
    }

    private void loadCartSummary() {
        subtotal = cartManager.getTotal();
        shipping = calculateShipping(subtotal);
        total = subtotal + shipping;
        
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        subtotalText.setText(currencyFormat.format(subtotal));
        shippingText.setText(currencyFormat.format(shipping));
        totalText.setText(currencyFormat.format(total));
    }

    private double calculateShipping(double subtotal) {
        return subtotal > 50 ? 0.0 : 5.99;
    }

    private void setupPlaceOrderButton() {
        placeOrderButton.setOnClickListener(v -> {
            if (validateInputs()) {
                placeOrderButton.setEnabled(false);
                showOrderProcessingDialog();
                processOrder();
            }
        });
    }

    private boolean validateInputs() {
        boolean isValid = true;
        
        // Validate shipping info
        if (fullNameInput.getText().toString().trim().isEmpty()) {
            fullNameInput.setError("Required");
            isValid = false;
        }
        if (addressInput.getText().toString().trim().isEmpty()) {
            addressInput.setError("Required");
            isValid = false;
        }
        if (cityInput.getText().toString().trim().isEmpty()) {
            cityInput.setError("Required");
            isValid = false;
        }
        if (zipCodeInput.getText().toString().trim().isEmpty()) {
            zipCodeInput.setError("Required");
            isValid = false;
        }

        // Payment method validation
        int selectedPaymentMethod = paymentMethodGroup.getCheckedRadioButtonId();
        if (selectedPaymentMethod == -1) {
            Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (selectedPaymentMethod == R.id.creditCardRadio) {
            // Validate credit card details
            if (cardNumberInput.getText().toString().trim().isEmpty()) {
                cardNumberInput.setError("Required");
                isValid = false;
            }
            if (expiryInput.getText().toString().trim().isEmpty()) {
                expiryInput.setError("Required");
                isValid = false;
            }
            if (cvvInput.getText().toString().trim().isEmpty()) {
                cvvInput.setError("Required");
                isValid = false;
            }
        } else if (selectedPaymentMethod == R.id.mpesaRadio) {
            // Validate M-PESA phone number
            if (phoneNumberInput.getText().toString().trim().isEmpty()) {
                phoneNumberInput.setError("Required");
                isValid = false;
            }
        }
        
        return isValid;
    }

    private void showOrderProcessingDialog() {
        OrderProcessingDialog dialog = new OrderProcessingDialog();
        dialog.show(getSupportFragmentManager(), "processing_dialog");
    }

    private void processOrder() {
        new Handler().postDelayed(() -> {
            OrderProcessingDialog processingDialog = 
                (OrderProcessingDialog) getSupportFragmentManager()
                    .findFragmentByTag("processing_dialog");
            if (processingDialog != null) {
                processingDialog.dismiss();
            }

            cartManager.clearCart();
            showOrderConfirmationDialog();
        }, 2000);
    }

    private void showOrderConfirmationDialog() {
        OrderConfirmationDialog confirmationDialog = new OrderConfirmationDialog();
        confirmationDialog.show(getSupportFragmentManager(), "confirmation_dialog");
        confirmationDialog.setOnDismissListener(() -> {
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