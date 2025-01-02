package com.example.mobile_application_programming.categories;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobile_application_programming.R;
import com.example.mobile_application_programming.account.AccountActivity;
import com.example.mobile_application_programming.auth.ContactActivity;
import com.example.mobile_application_programming.home.Product;
import com.example.mobile_application_programming.navigation.BottomNavigationBar;
import com.google.android.material.navigation.NavigationView;
import com.example.mobile_application_programming.cart.CartManager;
import com.example.mobile_application_programming.cart.CartActivity;
import java.util.ArrayList;
import java.util.List;
import com.example.mobile_application_programming.utils.GridSpacingItemDecoration;



public class CategoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationBar.NavigationListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RecyclerView productsRecyclerView;
    private CategoryAdapter categoryAdapter;
    private List<Product> allProducts;
    private BottomNavigationBar navigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Initialize views
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        productsRecyclerView = findViewById(R.id.productsRecyclerView);
        navigationBar = findViewById(R.id.bottomNavigation);
        Toolbar toolbar = findViewById(R.id.toolbar);

        // Setup toolbar
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.secondary_dark));

        // Setup navigation drawer
        setupNavigationDrawer(toolbar);

        // Setup bottom navigation
        navigationBar.setNavigationListener(this);
        navigationBar.setSelectedTab(1);

        // Setup RecyclerView
        setupRecyclerView();

        // Load all products initially
        loadAllProducts();
        filterProducts("All");
    }

    private void setupNavigationDrawer(Toolbar toolbar) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Setup navigation menu
        navigationView.setNavigationItemSelectedListener(this);
        setupCategoryMenu();
    }

    private void setupCategoryMenu() {
        navigationView.getMenu().clear();
        
        // Add categories with icons
        navigationView.getMenu().add(0, 0, 0, "All")
                .setIcon(R.drawable.ic_all)
                .setCheckable(true);
        navigationView.getMenu().add(0, 1, 0, "Electronics")
                .setIcon(R.drawable.ic_electronics)
                .setCheckable(true);
        navigationView.getMenu().add(0, 2, 0, "Clothing")
                .setIcon(R.drawable.ic_clothing)
                .setCheckable(true);
        navigationView.getMenu().add(0, 3, 0, "Accessories")
                .setIcon(R.drawable.ic_accessories)
                .setCheckable(true);
        navigationView.getMenu().add(0, 4, 0, "Footwear")
                .setIcon(R.drawable.ic_shoes)
                .setCheckable(true);
        navigationView.getMenu().add(0, 5, 0, "Gadgets")
                .setIcon(R.drawable.ic_gadgets)
                .setCheckable(true);
    }

    private void setupRecyclerView() {
        allProducts = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(this, allProducts, this::onAddToCart);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });
        productsRecyclerView.setLayoutManager(layoutManager);
        productsRecyclerView.setAdapter(categoryAdapter);
        
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
        productsRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels, true));
    }

    private void loadAllProducts() {
        allProducts.clear();
        
        // Electronics
        allProducts.add(new Product(1, "Smart Watch Pro", "199.99", 4.5f, R.drawable.watch));
        allProducts.add(new Product(2, "Wireless Earbuds", "129.99", 4.7f, R.drawable.earbuds));
        allProducts.add(new Product(3, "Bluetooth Speaker", "89.99", 4.6f, R.drawable.speaker));
        allProducts.add(new Product(4, "4K Smart TV", "699.99", 4.8f, R.drawable.tv));
        allProducts.add(new Product(5, "Gaming Laptop", "1299.99", 4.9f, R.drawable.laptop));
        
        // Clothing
        allProducts.add(new Product(6, "Classic T-Shirt", "29.99", 4.0f, R.drawable.tshirt));
        allProducts.add(new Product(7, "Denim Jeans", "59.99", 4.3f, R.drawable.jeans));
        allProducts.add(new Product(8, "Wool Sweater", "79.99", 4.4f, R.drawable.sweater));
        allProducts.add(new Product(9, "Winter Jacket", "129.99", 4.6f, R.drawable.jacket));
        allProducts.add(new Product(10, "Cotton Dress", "69.99", 4.2f, R.drawable.dress));
        
        // Accessories
        allProducts.add(new Product(11, "Leather Backpack", "79.99", 4.2f, R.drawable.backpack));
        allProducts.add(new Product(12, "Sunglasses", "49.99", 4.1f, R.drawable.sunglasses));
        allProducts.add(new Product(13, "Leather Wallet", "39.99", 4.3f, R.drawable.wallet));
        allProducts.add(new Product(14, "Silver Necklace", "89.99", 4.5f, R.drawable.necklace));
        allProducts.add(new Product(15, "Watch Collection", "299.99", 4.7f, R.drawable.watches));
        
        // Footwear
        allProducts.add(new Product(16, "Running Shoes", "89.99", 4.8f, R.drawable.shoes));
        allProducts.add(new Product(17, "Casual Sneakers", "69.99", 4.4f, R.drawable.sneakers));
        allProducts.add(new Product(18, "Leather Boots", "129.99", 4.6f, R.drawable.boots));
        allProducts.add(new Product(19, "Sport Sandals", "49.99", 4.2f, R.drawable.sandals));
        allProducts.add(new Product(20, "Formal Shoes", "99.99", 4.5f, R.drawable.formal_shoes));
        
        // Gadgets
        allProducts.add(new Product(21, "Fitness Tracker", "69.99", 4.6f, R.drawable.tracker));
        allProducts.add(new Product(22, "Wireless Mouse", "34.99", 4.3f, R.drawable.mouse));
        allProducts.add(new Product(23, "Power Bank", "49.99", 4.4f, R.drawable.powerbank));
        allProducts.add(new Product(24, "Phone Case", "24.99", 4.0f, R.drawable.phonecase));
        allProducts.add(new Product(25, "Laptop Sleeve", "39.99", 4.2f, R.drawable.sleeve));
    }

    private void filterProducts(String category) {
        List<Product> filteredList = new ArrayList<>();
        
        if (category.equals("All")) {
            filteredList.addAll(allProducts);
        } else {
            for (Product product : allProducts) {
                if (category.equals("Electronics") && 
                    (product.getName().contains("Watch") || 
                     product.getName().contains("Earbuds") || 
                     product.getName().contains("Speaker") || 
                     product.getName().contains("TV") || 
                     product.getName().contains("Laptop"))) {
                    filteredList.add(product);
                } else if (category.equals("Clothing") && 
                         (product.getName().contains("T-Shirt") || 
                          product.getName().contains("Jeans") || 
                          product.getName().contains("Sweater") || 
                          product.getName().contains("Jacket") || 
                          product.getName().contains("Dress"))) {
                    filteredList.add(product);
                } else if (category.equals("Accessories") && 
                         (product.getName().contains("Backpack") || 
                          product.getName().contains("Sunglasses") || 
                          product.getName().contains("Wallet") || 
                          product.getName().contains("Necklace"))) {
                    filteredList.add(product);
                } else if (category.equals("Footwear") && 
                         (product.getName().contains("Shoes") || 
                          product.getName().contains("Sneakers") || 
                          product.getName().contains("Boots") || 
                          product.getName().contains("Sandals"))) {
                    filteredList.add(product);
                } else if (category.equals("Gadgets") && 
                         (product.getName().contains("Tracker") || 
                          product.getName().contains("Mouse") || 
                          product.getName().contains("Power Bank") || 
                          product.getName().contains("Case") || 
                          product.getName().contains("Sleeve"))) {
                    filteredList.add(product);
                }
            }
        }
        
        categoryAdapter.updateProducts(filteredList);
    }

    private void onAddToCart(Product product) {
        CartManager.getInstance(this).addToCart(product);
        Toast.makeText(this, product.getName() + " added to cart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        filterProducts(item.getTitle().toString());
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                finish(); // Go back to home
                break;
            case 1:
                // Already on categories
                break;
            case 2:
                 // Navigate to cart
            Intent cartIntent = new Intent(this, CartActivity.class);
            startActivity(cartIntent);
            break;
            case 3:
                // Navigate to contact
                Intent contactIntent = new Intent(this, ContactActivity.class);
                startActivity(contactIntent);
                break;
            case 4:
                // navigate to account
                Intent accountIntent = new Intent (this , AccountActivity.class);
                startActivity(accountIntent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}