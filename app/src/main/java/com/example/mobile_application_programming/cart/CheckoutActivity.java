package com.example.mobile_application_programming.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mobile_application_programming.R;
import com.example.mobile_application_programming.home.HomeActivity;
import java.text.NumberFormat;
import java.util.Locale;

public class CheckoutActivity extends AppCompatActivity {
    private EditText nameInput;
    private EditText emailInput;
    private EditText phoneInput;
    private EditText addressInput;
    private RadioGroup paymentMethodGroup;
    private TextView totalAmountText;
    private Button placeOrderButton;
    private CartManager cartManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Initialize views
        initializeViews();
        cartManager = CartManager.getInstance(this);

        // Display total amount
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        totalAmountText.setText("Total Amount: " + currencyFormat.format(cartManager.getTotal()));

        // Set up place order button
        placeOrderButton.setOnClickListener(v -> {
            if (validateInputs()) {
                processOrder();
            }
        });
    }

    private void initializeViews() {
        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        phoneInput = findViewById(R.id.phoneInput);
        addressInput = findViewById(R.id.addressInput);
        paymentMethodGroup = findViewById(R.id.paymentMethodGroup);
        totalAmountText = findViewById(R.id.totalAmountText);
        placeOrderButton = findViewById(R.id.placeOrderButton);
    }

    private boolean validateInputs() {
        if (nameInput.getText().toString().trim().isEmpty()) {
            nameInput.setError("Name is required");
            return false;
        }
        if (emailInput.getText().toString().trim().isEmpty() || 
            !android.util.Patterns.EMAIL_ADDRESS.matcher(emailInput.getText().toString()).matches()) {
            emailInput.setError("Valid email is required");
            return false;
        }
        if (phoneInput.getText().toString().trim().isEmpty()) {
            phoneInput.setError("Phone number is required");
            return false;
        }
        if (addressInput.getText().toString().trim().isEmpty()) {
            addressInput.setError("Shipping address is required");
            return false;
        }
        if (paymentMethodGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void processOrder() {
        // Show order processing dialog
        OrderProcessingDialog processingDialog = new OrderProcessingDialog(this);
        processingDialog.show();

        // Simulate order processing
        new android.os.Handler().postDelayed(() -> {
            processingDialog.dismiss();
            showOrderConfirmation();
        }, 2000);
    }

    private void showOrderConfirmation() {
        OrderConfirmationDialog confirmationDialog = new OrderConfirmationDialog(this);
        confirmationDialog.setOnDismissListener(dialog -> {
            // Clear cart and return to home
            cartManager.clearCart();
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
        confirmationDialog.show();
    }
}