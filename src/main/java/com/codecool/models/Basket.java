package com.codecool.models;

import java.util.ArrayList;

public class Basket {
    private int id;
    private ArrayList<Product> products;

    public Basket(int id, ArrayList<Product> products) {
        this.id = id;
        this.products = products;
    }

    public Basket(int id, int productId, int orderId, int quantity) {

    }
}
