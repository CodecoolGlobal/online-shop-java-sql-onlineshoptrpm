package com.codecool.models;

import com.codecool.IO;
import com.codecool.dao.BasketDao;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class Basket {
    private int id;
    private List<Product> products;
    private int orderID;
    private int productId;
    private int quantity;
    private Iterator<Product> basketIterator;


    public Basket(int id, List<Product> products) throws SQLException {
        this.id = id;
        this.products = products;
        this.orderID = generateOrderID();
    }

    public Basket(int id, int productId, int orderId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.orderID = orderId;
        this.quantity = quantity;
    }

    private int generateOrderID() throws SQLException {
        IO io = new IO();
        BasketDao basketDao = new BasketDao();
        int randomInt = 1;
        List<Integer> forbiddenInts = basketDao.getAllOrdersID();
        if (forbiddenInts.size() != 0) {
            do {
                randomInt = io.generateRandomNumber();
            } while (!forbiddenInts.contains(randomInt));
        }
        return randomInt;
    }

    public void addProduct(Product product, int amount) {
        int productID = product.getId();
        for (Product prod : products) {
            if (productID == prod.getId()) {
                prod.setAmount(amount + prod.getAmount());
                return;
            }
        }
        product.setAmount(amount);
        products.add(product);
    }

    public void deleteProduct(Product product) {
        products.remove(product);
    }

    public void setProductQuantity(Product product, int quantity) {
        product.setAmount(quantity);
    }

    public Iterator<Product> getIterator() {
        basketIterator = new ProductIterator(products);
        return basketIterator;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
