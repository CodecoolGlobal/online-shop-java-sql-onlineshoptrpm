package com.codecool.models;

import java.util.ArrayList;

public class Category {
    private int id;
    private String name;
    private boolean isAvailable;
    private ArrayList<Product> products;
//    private int categoryID;
//    private String newCategoryName;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
        this.isAvailable = true;
//        this.categoryID = categoryID;
//        this.newCategoryName = newCategoryName;
    }


    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
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
