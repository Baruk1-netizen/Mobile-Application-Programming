package com.example.mobile_application_programming.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_application_programming.R;
import com.example.mobile_application_programming.home.HomeActivity;
import com.google.android.material.button.MaterialButton;

public class RegisterActivity extends AppCompatActivity {
    private EditText emailField, passwordField, confirmPasswordField;
    private MaterialButton registerButton, googleSignInButton;
    private TextView loginText;
    private ImageView topPulse, bottomPulse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        // Initialize views
        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);
        confirmPasswordField = findViewById(R.id.confirmPasswordField);
        registerButton = findViewById(R.id.registerButton);
        googleSignInButton = findViewById(R.id.googleSignInButton);
        loginText = findViewById(R.id.loginText);
        topPulse = findViewById(R.id.topPulse);
        bottomPulse = findViewById(R.id.bottomPulse);

        registerButton.setOnClickListener(v -> attemptRegister());
        googleSignInButton.setOnClickListener(v -> signInWithGoogle());
        loginText.setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }

    private void attemptRegister() {
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        String confirmPassword = confirmPasswordField.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidEmail(email)) {
            emailField.setError("Invalid Email");
            emailField.requestFocus();
            return;
        }

        if (password.length() < 6) {
            passwordField.setError("Password must be at least 6 characters");
            passwordField.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            confirmPasswordField.setError("Passwords do not match");
            confirmPasswordField.requestFocus();
            return;
        }


    }

    private void signInWithGoogle() {
        // Implement Google Sign-In logic
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
