package com.example.mobile_application_programming.home;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private int id;
    private String name;
    private String price;
    private float rating;
    private int imageResource;
    private String description;
    private int quantity;

    public Product(int id, String name, String price, float rating, int imageResource) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.imageResource = imageResource;
        this.quantity = 1;
        this.description = "High-quality product with excellent features."; // Default description
    }

    protected Product(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readString();
        rating = in.readFloat();
        imageResource = in.readInt();
        description = in.readString();
        quantity = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    // Getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getPrice() { return price; }
    public float getRating() { return rating; }
    public int getImageResource() { return imageResource; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPriceAsDouble() {
        try {
            return Double.parseDouble(price);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeFloat(rating);
        dest.writeInt(imageResource);
        dest.writeString(description);
        dest.writeInt(quantity);
    }

    public int getImageResourceId() {
        return imageResource; // Replace with actual image resource ID calculation
    }
}