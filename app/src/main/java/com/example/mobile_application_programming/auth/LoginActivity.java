package com.example.mobile_application_programming.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mobile_application_programming.R;
import com.example.mobile_application_programming.home.HomeActivity;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {
    private EditText emailField, passwordField;
    private MaterialButton loginButton, googleSignInButton;
    private TextView signUpText;
    private ImageView topPulse, bottomPulse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);
        loginButton = findViewById(R.id.loginButton);
        googleSignInButton = findViewById(R.id.googleSignInButton);
        signUpText = findViewById(R.id.signUpText);
        topPulse = findViewById(R.id.topPulse);
        bottomPulse = findViewById(R.id.bottomPulse);

        // Animations
        topPulse.startAnimation(AnimationUtils.loadAnimation(this, R.anim.pulse));
        bottomPulse.startAnimation(AnimationUtils.loadAnimation(this, R.anim.pulse));

        // Click listeners
        loginButton.setOnClickListener(v -> attemptLogin());
        googleSignInButton.setOnClickListener(v -> signInWithGoogle());
        signUpText.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }

    private void attemptLogin() {
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Temporary: Direct login with hardcoded user
        if ("user1".equals(email) && "1234".equals(password)) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
    }

    private void signInWithGoogle() {
        // Temporary: Direct login without Google authentication
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }
}
