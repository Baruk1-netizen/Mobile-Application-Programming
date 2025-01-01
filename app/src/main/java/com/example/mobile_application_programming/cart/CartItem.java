package com.example.mobile_application_programming.cart;

import java.io.Serializable;

public class CartItem implements Serializable {
    private int id;
    private String name;
    private String price;
    private float rating;
    private int imageResource;
    private int quantity;
    
    // Constructor that takes a Product
    public CartItem(int id, String name, String price, float rating, int imageResource) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.imageResource = imageResource;
        this.quantity = 1;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getPrice() { return price; }
    public float getRating() { return rating; }
    public int getImageResource() { return imageResource; }
    public int getQuantity() { return quantity; }
    
    // Get total price as double
    public double getTotalPrice() {
        return Double.parseDouble(price) * quantity;
    }

    // Setters
    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        }
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public void decrementQuantity() {
        if (this.quantity > 1) {
            this.quantity--;
        }
    }
}