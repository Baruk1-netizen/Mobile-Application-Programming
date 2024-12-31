package com.example.mobile_application_programming.cart;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.mobile_application_programming.home.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static final String PREF_NAME = "CartPreferences";
    private static final String CART_ITEMS = "cart_items";
    private static CartManager instance;
    private final SharedPreferences preferences;
    private final Gson gson;
    private List<Product> cartItems;

    private CartManager(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
        loadCartItems();
    }

    public static synchronized CartManager getInstance(Context context) {
        if (instance == null) {
            instance = new CartManager(context.getApplicationContext());
        }
        return instance;
    }

    private void loadCartItems() {
        String json = preferences.getString(CART_ITEMS, null);
        Type type = new TypeToken<ArrayList<Product>>() {}.getType();
        cartItems = gson.fromJson(json, type);
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
    }

    private void saveCartItems() {
        String json = gson.toJson(cartItems);
        preferences.edit().putString(CART_ITEMS, json).apply();
    }

    public void addToCart(Product product) {
        boolean found = false;
        for (Product item : cartItems) {
            if (item.getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                found = true;
                break;
            }
        }
        if (!found) {
            product.setQuantity(1);
            cartItems.add(product);
        }
        saveCartItems();
    }

    public void removeFromCart(Product product) {
        cartItems.removeIf(item -> item.getId() == product.getId());
        saveCartItems();
    }

    public void updateQuantity(Product product, int quantity) {
        for (Product item : cartItems) {
            if (item.getId() == product.getId()) {
                item.setQuantity(quantity);
                break;
            }
        }
        saveCartItems();
    }

    public List<Product> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public void clearCart() {
        cartItems.clear();
        saveCartItems();
    }

    public double getCartTotal() {
        double total = 0;
        for (Product item : cartItems) {
            total += item.getPriceAsDouble() * item.getQuantity();
        }
        return total;
    }

    public int getCartSize() {
        return cartItems.size();
    }
}
