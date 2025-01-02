package com.example.mobile_application_programming.account;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_application_programming.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;

public class AccountActivity extends AppCompatActivity {
    private ShapeableImageView profileImage;
    private TextInputEditText fullNameInput, emailInput, phoneInput;
    private TextInputEditText addressLine1Input, cityInput, stateInput, zipCodeInput;
    private MaterialButton btnSave;
    private ActivityResultLauncher<String> imagePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        initializeViews();
        setupImagePicker();
        loadUserData();
        setupListeners();
    }

    private void initializeViews() {
        profileImage = findViewById(R.id.profileImage);
        fullNameInput = findViewById(R.id.fullNameInput);
        emailInput = findViewById(R.id.emailInput);
        phoneInput = findViewById(R.id.phoneInput);
        addressLine1Input = findViewById(R.id.addressLine1Input);
        cityInput = findViewById(R.id.cityInput);
        stateInput = findViewById(R.id.stateInput);
        zipCodeInput = findViewById(R.id.zipCodeInput);
        btnSave = findViewById(R.id.btnSave);
    }

    private void setupImagePicker() {
        imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    profileImage.setImageURI(uri);
                    // TODO: Upload image to server
                }
            }
        );

        findViewById(R.id.btnChangePhoto).setOnClickListener(v ->
            imagePickerLauncher.launch("image/*")
        );
    }

    private void loadUserData() {
        // TODO: Load user data from database or shared preferences
        // This is where you would populate the fields with existing user data
    }

    private void setupListeners() {
        btnSave.setOnClickListener(v -> saveUserData());
    }

    private void saveUserData() {
        // Validate inputs
        if (!validateInputs()) {
            return;
        }

        // Create user data object
        UserData userData = new UserData(
            fullNameInput.getText().toString(),
            emailInput.getText().toString(),
            phoneInput.getText().toString(),
            addressLine1Input.getText().toString(),
            cityInput.getText().toString(),
            stateInput.getText().toString(),
            zipCodeInput.getText().toString()
        );

        // TODO: Save user data to database or shared preferences
        // For now, just show a success message
        Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
    }

    private boolean validateInputs() {
        boolean isValid = true;

        if (fullNameInput.getText().toString().trim().isEmpty()) {
            fullNameInput.setError("Name is required");
            isValid = false;
        }

        if (emailInput.getText().toString().trim().isEmpty()) {
            emailInput.setError("Email is required");
            isValid = false;
        }

        // Add more validation as needed

        return isValid;
    }

    // Data class to hold user information
    private static class UserData {
        String fullName;
        String email;
        String phone;
        String addressLine1;
        String city;
        String state;
        String zipCode;

        UserData(String fullName, String email, String phone, String addressLine1,
                String city, String state, String zipCode) {
            this.fullName = fullName;
            this.email = email;
            this.phone = phone;
            this.addressLine1 = addressLine1;
            this.city = city;
            this.state = state;
            this.zipCode = zipCode;
        }
    }
}