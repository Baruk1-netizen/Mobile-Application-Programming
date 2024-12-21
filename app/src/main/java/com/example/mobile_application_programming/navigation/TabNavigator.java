package com.example.mobile_application_programming.navigation;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;

import com.example.mobile_application_programming.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.mobile_application_programming.home.HomeActivity;

public class TabNavigator {
    private final Context context;
    private final BottomNavigationView bottomNavigationView;

    public TabNavigator(@NonNull Context context, @NonNull BottomNavigationView bottomNavigationView) {
        this.context = context;
        this.bottomNavigationView = bottomNavigationView;
        setupNavigation();
    }

    private void setupNavigation() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            
            // For now, we'll just navigate to Home and show TODOs for others
            if (itemId == R.id.navigation_home) {
                context.startActivity(new Intent(context, HomeActivity.class));
                return true;
            } else if (itemId == R.id.navigation_category) {
                // TODO: Navigate to Category when implemented
                return true;
            } else if (itemId == R.id.navigation_cart) {
                // TODO: Navigate to Cart when implemented
                return true;
            } else if (itemId == R.id.navigation_profile) {
                // TODO: Navigate to Profile when implemented
                return true;
            } else if (itemId == R.id.navigation_message) {
                // TODO: Navigate to Message when implemented
                return true;
            }
            return false;
        });
    }

    public void setSelectedItem(int itemId) {
        bottomNavigationView.setSelectedItemId(itemId);
    }
}