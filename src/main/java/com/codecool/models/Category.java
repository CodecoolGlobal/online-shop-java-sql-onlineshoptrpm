package com.codecool.models;

import java.util.ArrayList;

public class Category {
    private int id;
    private String name;
    private int isAvailable = 1;
    private ArrayList<Product> products;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int isAvailable() {
        return isAvailable;
    }

    public void setAvailable(int available) {
        this.isAvailable = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
