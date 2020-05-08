package com.codecool.models;

public class Product {

    private int id;
    private String name;
    private float price;
    private int amount;
    private int isAvailable;
    private int category;

    public Product(int id, String name, float price, int amount, int isAvailable, int category) {
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

    public int isAvailable() {
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

    public void setPrice(float price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount += amount;
    }

    public void setAvailable(int available) {
        isAvailable = available;
    }

    public void setCategory(int category) {
        this.category = category;
    }

}
