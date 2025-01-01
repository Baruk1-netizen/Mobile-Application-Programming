package com.example.mobile_application_programming.home;

public class Product {
    private final int id;
    private final String name;
    private final String price;
    private final float rating;
    private final int imageResourceId;
    private final String description;

    public Product(int id, String name, String price, float rating, int imageResourceId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.imageResourceId = imageResourceId;
        this.description = generateDescription(name);
    }

    private String generateDescription(String name) {
        return "Experience premium quality with the " + name + 
               ". This product offers excellent value for money with its " +
               "superior features and elegant design. Made with high-quality materials " +
               "and attention to detail, this product is designed to exceed your expectations.";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public float getRating() {
        return rating;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResource() {
        return imageResourceId; 
    }
}