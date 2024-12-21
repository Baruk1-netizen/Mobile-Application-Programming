package com.example.mobile_application_programming;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_DURATION = 3000; // Total duration
    private static final int IMAGE_FADE_DURATION = 1000; // Image fade duration
    private static final int IMAGE_FADE_START_DELAY = 500; // Delay before image starts fading in

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView splashLogo = findViewById(R.id.splash_logo);
        
        // Start with invisible image
        splashLogo.setAlpha(0f);

        // Create handler for delayed animation
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            // Animate the image
            splashLogo.animate()
                    .alpha(1f)
                    .setDuration(IMAGE_FADE_DURATION)
                    .start();
        }, IMAGE_FADE_START_DELAY);

        // Navigate to main activity
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }, SPLASH_DURATION);
    }
}