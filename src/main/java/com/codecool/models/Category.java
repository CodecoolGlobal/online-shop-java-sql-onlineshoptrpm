package com.codecool.models;

import java.util.ArrayList;

public class Category {
    private int id;
    private String name;
    private int isAvailable;
    private ArrayList<Product> products;
//    private int categoryID;
//    private String newCategoryName;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
        this.isAvailable = 1;
//        this.categoryID = categoryID;
//        this.newCategoryName = newCategoryName;
    }


    public int isAvailable() {
        return 1;
    }

    public void setAvailable(int available) {
        this.isAvailable = available;
    }

    public int getIsAvailable() {
        return isAvailable;
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
