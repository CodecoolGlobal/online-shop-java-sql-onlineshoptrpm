package com.codecool.models;

public class Product {

    private int id;
    private String name;
    private float price;
    private int amount;
    private boolean isAvailable;
    private int category;

    public Product(int id, String name, float price, int amount, boolean isAvailable, int category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.isAvailable = isAvailable;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getCategory() {
        return category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setCategory(int category) {
        this.category = category;
    }

}
