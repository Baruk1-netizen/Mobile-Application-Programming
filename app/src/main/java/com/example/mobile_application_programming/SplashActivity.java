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

import com.example.mobile_application_programming.home.HomeActivity;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_DURATION = 5000;
    private static final int ANIMATION_DURATION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        View gradientBL = findViewById(R.id.gradient_bl);
        View gradientTR = findViewById(R.id.gradient_tr);
        View logo = findViewById(R.id.logo);
        View shopIcon = findViewById(R.id.shop_icon);

        gradientBL.setAlpha(0.6f);
        gradientTR.setAlpha(0.6f);
        logo.setAlpha(1f);
        shopIcon.setAlpha(1f);

        ObjectAnimator scaleLogoX = ObjectAnimator.ofFloat(logo, "scaleX", 0.98f, 1f);
        ObjectAnimator scaleLogoY = ObjectAnimator.ofFloat(logo, "scaleY", 0.98f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleLogoX, scaleLogoY);
        animatorSet.setDuration(ANIMATION_DURATION);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.start();

        // Navigate to HomeActivity instead of MainActivity
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }, SPLASH_DURATION);
    }
}