package com.codecool.models;

import java.util.Iterator;
import java.util.List;

public class ProductIterator implements Iterator<Product> {
    private final List<Product> products;
    private int currentPosition;

    public ProductIterator(List<Product> products) {
        this.products = products;
        this.currentPosition = 0;
    }

    public boolean hasNext() {
        if (this.currentPosition < this.products.size()) {
            return true;
        } else {
            this.currentPosition = 0;
            return false;
        }
    }

    public Product next() {

        Product product = this.products.get(this.currentPosition);
        currentPosition++;
        return product;

    }
}