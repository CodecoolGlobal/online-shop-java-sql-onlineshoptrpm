package com.codecool.models;

import java.util.List;

public class Basket {
    private int id;
    private List<Product> products;

    public Basket(int id, List<Product> products) {
        this.id = id;
        this.products = products;
    }

    public Basket(int id, int productId, int orderId, int quantity) {

    }
}
