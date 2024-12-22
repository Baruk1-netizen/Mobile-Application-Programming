package com.example.mobile_application_programming.navigation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mobile_application_programming.R;

public class BottomNavigationBar extends RelativeLayout {
    private LinearLayout homeTab, categoriesTab, messagesTab, accountTab;
    private FrameLayout cartTab;
    private View homeDot, categoriesDot, messagesDot, accountDot;
    private ImageView homeIcon, categoriesIcon, cartIcon, messagesIcon, accountIcon;
    private TextView homeText, categoriesText, messagesText, accountText;
    private NavigationListener listener;
    private int selectedTab = 0;

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
        messagesTab = findViewById(R.id.messagesTab);
        accountTab = findViewById(R.id.accountTab);

        homeDot = findViewById(R.id.homeDot);
        categoriesDot = findViewById(R.id.categoriesDot);
        messagesDot = findViewById(R.id.messagesDot);
        accountDot = findViewById(R.id.accountDot);

        homeIcon = findViewById(R.id.homeIcon);
        categoriesIcon = findViewById(R.id.categoriesIcon);
        cartIcon = findViewById(R.id.cartIcon);
        messagesIcon = findViewById(R.id.messagesIcon);
        accountIcon = findViewById(R.id.accountIcon);

        homeText = findViewById(R.id.homeText);
        categoriesText = findViewById(R.id.categoriesText);
        messagesText = findViewById(R.id.messagesText);
        accountText = findViewById(R.id.accountText);

        setupClickListeners();
        setSelectedTab(0); // Set home as default
    }

    private void setupClickListeners() {
        homeTab.setOnClickListener(v -> handleTabClick(0));
        categoriesTab.setOnClickListener(v -> handleTabClick(1));
        cartTab.setOnClickListener(v -> handleTabClick(2));
        messagesTab.setOnClickListener(v -> handleTabClick(3));
        accountTab.setOnClickListener(v -> handleTabClick(4));
    }

    private void handleTabClick(int position) {
        if (position != selectedTab) {
            setSelectedTab(position);
            if (listener != null) {
                listener.onTabSelected(position);
            }
        }
    }

    public void setNavigationListener(NavigationListener listener) {
        this.listener = listener;
    }

    private void setSelectedTab(int position) {
        // Reset all tabs
        resetAllTabs();

        // Set selected tab
        switch (position) {
            case 0:
                setTabActive(homeDot, homeIcon, homeText);
                break;
            case 1:
                setTabActive(categoriesDot, categoriesIcon, categoriesText);
                break;
            case 3:
                setTabActive(messagesDot, messagesIcon, messagesText);
                break;
            case 4:
                setTabActive(accountDot, accountIcon, accountText);
                break;
            // Cart (position 2) stays the same color
        }

        selectedTab = position;
    }

    private void resetAllTabs() {
        homeDot.setVisibility(INVISIBLE);
        categoriesDot.setVisibility(INVISIBLE);
        messagesDot.setVisibility(INVISIBLE);
        accountDot.setVisibility(INVISIBLE);

        int inactiveColor = getResources().getColor(R.color.secondary_dark);
        homeIcon.setColorFilter(inactiveColor);
        categoriesIcon.setColorFilter(inactiveColor);
        messagesIcon.setColorFilter(inactiveColor);
        accountIcon.setColorFilter(inactiveColor);

        homeText.setTextColor(inactiveColor);
        categoriesText.setTextColor(inactiveColor);
        messagesText.setTextColor(inactiveColor);
        accountText.setTextColor(inactiveColor);
    }

    private void setTabActive(View dot, ImageView icon, TextView text) {
        dot.setVisibility(VISIBLE);
        icon.setColorFilter(getResources().getColor(R.color.primary_coral));
        text.setTextColor(getResources().getColor(R.color.primary_coral));
    }
}