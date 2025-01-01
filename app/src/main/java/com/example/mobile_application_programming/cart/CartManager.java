package com.example.mobile_application_programming.cart;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.mobile_application_programming.home.Product;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static final String PREF_NAME = "CartPrefs";
    private static final String CART_ITEMS = "cart_items";
    private static CartManager instance;
    private final SharedPreferences prefs;
    private final Gson gson;
    private List<CartItem> cartItems;
    private List<CartUpdateListener> listeners;

    public interface CartUpdateListener {
        void onCartUpdated();
    }

    private CartManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
        listeners = new ArrayList<>();
        loadCart();
    }

    public static synchronized CartManager getInstance(Context context) {
        if (instance == null) {
            instance = new CartManager(context.getApplicationContext());
        }
        return instance;
    }

    private void loadCart() {
        String json = prefs.getString(CART_ITEMS, null);
        Type type = new TypeToken<ArrayList<CartItem>>(){}.getType();
        cartItems = json == null ? new ArrayList<>() : gson.fromJson(json, type);
    }

    private void saveCart() {
        String json = gson.toJson(cartItems);
        prefs.edit().putString(CART_ITEMS, json).apply();
        notifyListeners();
    }

    public void addListener(CartUpdateListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void removeListener(CartUpdateListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        for (CartUpdateListener listener : listeners) {
            listener.onCartUpdated();
        }
    }

    // Method to add a Product to cart
    public void addToCart(Product product) {
        // Check if product already exists in cart
        for (CartItem item : cartItems) {
            if (item.getId() == product.getId()) {
                item.incrementQuantity();
                saveCart();
                return;
            }
        }
        
        // If product doesn't exist, create new cart item
        CartItem newItem = new CartItem(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getRating(),
            product.getImageResource()
        );
        cartItems.add(newItem);
        saveCart();
    }

    public void removeItem(int productId) {
        cartItems.removeIf(item -> item.getId() == productId);
        saveCart();
    }

    public void updateItemQuantity(int productId, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getId() == productId) {
                item.setQuantity(quantity);
                saveCart();
                return;
            }
        }
    }

    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public double getTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public void clearCart() {
        cartItems.clear();
        saveCart();
    }

    public int getItemCount() {
        return cartItems.size();
    }
}