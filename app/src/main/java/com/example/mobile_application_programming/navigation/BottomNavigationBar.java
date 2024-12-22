package com.example.mobile_application_programming.navigation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.example.mobile_application_programming.R;

public class BottomNavigationBar extends RelativeLayout {
    private View homeDot, categoriesDot, accountDot, profileDot;
    private LinearLayout homeTab, categoriesTab, accountTab, profileTab;
    private View cartTab;
    private NavigationListener listener;

    public interface NavigationListener {
        void onTabSelected(int position);
    }

    public BottomNavigationBar(Context context) {
        super(context);
        init(context);
    }

    public BottomNavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.bottom_navigation_layout, this, true);
        
        // Initialize views
        homeTab = findViewById(R.id.homeTab);
        categoriesTab = findViewById(R.id.categoriesTab);
        cartTab = findViewById(R.id.cartTab);
        accountTab = findViewById(R.id.accountTab);
        profileTab = findViewById(R.id.profileTab);

        homeDot = findViewById(R.id.homeDot);
        categoriesDot = findViewById(R.id.categoriesDot);
        accountDot = findViewById(R.id.accountDot);
        profileDot = findViewById(R.id.profileDot);

        // Set click listeners
        homeTab.setOnClickListener(v -> selectTab(0));
        categoriesTab.setOnClickListener(v -> selectTab(1));
        cartTab.setOnClickListener(v -> selectTab(2));
        accountTab.setOnClickListener(v -> selectTab(3));
        profileTab.setOnClickListener(v -> selectTab(4));

        // Set initial selection
        selectTab(0);
    }

    public void setNavigationListener(NavigationListener listener) {
        this.listener = listener;
    }

    private void selectTab(int position) {
        // Reset all dots
        homeDot.setVisibility(INVISIBLE);
        categoriesDot.setVisibility(INVISIBLE);
        accountDot.setVisibility(INVISIBLE);
        profileDot.setVisibility(INVISIBLE);

        // Show selected dot
        switch (position) {
            case 0:
                homeDot.setVisibility(VISIBLE);
                break;
            case 1:
                categoriesDot.setVisibility(VISIBLE);
                break;
            case 2:
                // Cart has no dot
                break;
            case 3:
                accountDot.setVisibility(VISIBLE);
                break;
            case 4:
                profileDot.setVisibility(VISIBLE);
                break;
        }

        if (listener != null) {
            listener.onTabSelected(position);
        }
    }
}