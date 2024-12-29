package com.example.mobile_application_programming;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_application_programming.auth.LoginActivity;
import com.example.mobile_application_programming.home.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_DURATION = 5000;
    private static final int ANIMATION_DURATION = 1000;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize views
        View gradientBL = findViewById(R.id.gradient_bl);
        View gradientTR = findViewById(R.id.gradient_tr);
        View logo = findViewById(R.id.logo);
        View shopIcon = findViewById(R.id.shop_icon);

        // Set initial alpha values
        gradientBL.setAlpha(0.6f);
        gradientTR.setAlpha(0.6f);
        logo.setAlpha(0f);
        shopIcon.setAlpha(0f);

        // Create fade-in animations for logo and shop icon
        ObjectAnimator fadeInLogo = ObjectAnimator.ofFloat(logo, "alpha", 0f, 1f);
        ObjectAnimator fadeInShopIcon = ObjectAnimator.ofFloat(shopIcon, "alpha", 0f, 1f);

        // Create scale animations for logo
        ObjectAnimator scaleLogoX = ObjectAnimator.ofFloat(logo, "scaleX", 0.8f, 1f);
        ObjectAnimator scaleLogoY = ObjectAnimator.ofFloat(logo, "scaleY", 0.8f, 1f);

        // Create scale animations for shop icon
        ObjectAnimator scaleShopIconX = ObjectAnimator.ofFloat(shopIcon, "scaleX", 0.8f, 1f);
        ObjectAnimator scaleShopIconY = ObjectAnimator.ofFloat(shopIcon, "scaleY", 0.8f, 1f);

        // Create gradient animations
        ObjectAnimator gradientBLAlpha = ObjectAnimator.ofFloat(gradientBL, "alpha", 0f, 0.6f);
        ObjectAnimator gradientTRAlpha = ObjectAnimator.ofFloat(gradientTR, "alpha", 0f, 0.6f);

        // Combine all animations
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
            fadeInLogo, fadeInShopIcon,
            scaleLogoX, scaleLogoY,
            scaleShopIconX, scaleShopIconY,
            gradientBLAlpha, gradientTRAlpha
        );
        animatorSet.setDuration(ANIMATION_DURATION);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.start();

        // Start pulsing animation for logo
        startPulsingAnimation(logo);
        startPulsingAnimation(shopIcon);

        // Navigate to appropriate screen based on auth state
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            Intent intent;
            
            if (currentUser != null && currentUser.isEmailVerified()) {
                // User is signed in and email is verified
                intent = new Intent(SplashActivity.this, HomeActivity.class);
            } else {
                // No user is signed in or email not verified
                intent = new Intent(SplashActivity.this, LoginActivity.class);
            }
            
            startActivity(intent);
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }, SPLASH_DURATION);
    }

    private void startPulsingAnimation(View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.05f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.05f);

        AnimatorSet pulseAnimator = new AnimatorSet();
        pulseAnimator.playTogether(scaleX, scaleY);
        pulseAnimator.setDuration(1000);
        pulseAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        pulseAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        pulseAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        pulseAnimator.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Clean up any animations if activity is paused
        if (isFinishing()) {
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }
}