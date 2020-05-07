package com.codecool.models;

import com.codecool.IO;
import com.codecool.dao.BasketDao;
import com.jakewharton.fliptables.FlipTable;

import java.sql.SQLException;
import java.util.ArrayList;
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

    public void seeAllProductsInBasket() {
        String[] innerHeaders = {"Name", "Price", "Amount", "Total Price"};
        String[][] innerData = createInnerData();
        String inner = FlipTable.of(innerHeaders, innerData);
        String[] headers = getTotalExpenses();
        String[][] data = {{inner}};
        System.out.println(FlipTable.of(headers, data));
    }

    private String[][] createInnerData() {
        List<String[]> innerData = new ArrayList<>();
        for (Product product : products) {
            String[] temp = new String[4];
            temp[0] = product.getName();
            temp[1] = String.valueOf(product.getPrice());
            temp[2] = String.valueOf(product.getAmount());
            temp[3] = String.valueOf(product.getPrice() * product.getAmount());
            innerData.add(temp);
        }
        return convertTo2DArray(innerData);
    }

    private String[][] convertTo2DArray(List<String[]> innerData) {
        String[][] array2D = new String[innerData.size()][];
        for (int i = 0; i < array2D.length; i++) {
            String[] row = innerData.get(i);
            array2D[i] = row;
        }
        return array2D;
    }

    private String[] getTotalExpenses() {
        int totalExpenses = 0;
        for (Product product : products) {
            totalExpenses += (product.getPrice() * product.getAmount());
        }
        return new String[]{"Total Expenses: " + totalExpenses};
    }
}
