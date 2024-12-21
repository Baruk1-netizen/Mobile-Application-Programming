package com.example.mobile_application_programming.home;

public class Product {
    private int id;
    private String name;
    private String price;
    private float rating;
    private int imageResource;

    public Product(int id, String name, String price, float rating, int imageResource) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.imageResource = imageResource;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getPrice() { return price; }
    public float getRating() { return rating; }
    public int getImageResource() { return imageResource; }
}