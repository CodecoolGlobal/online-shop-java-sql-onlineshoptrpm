package com.codecool.models;

import com.codecool.IO;
import com.codecool.dao.BasketDao;

import java.sql.SQLException;
import java.util.List;

public class Basket {
    private int id;
    private List<Product> products;
    private int orderID;

    public Basket(int id, List<Product> products) throws SQLException {
        this.id = id;
        this.products = products;
        this.orderID = generateOrderID();
    }

    private int generateOrderID() throws SQLException {
        IO io = new IO();
        BasketDao basketDao = new BasketDao();
        int randomInt;
        List<Integer> forbiddenInts = basketDao.getAllOrdersID();
        do {
            randomInt = io.generateRandomNumber();
        } while (!forbiddenInts.contains(randomInt));
        return randomInt;
    }

    public Basket(int id, int productId, int orderId, int quantity) {

    }

    public void addProduct(Product product, int amount){

    }
}
