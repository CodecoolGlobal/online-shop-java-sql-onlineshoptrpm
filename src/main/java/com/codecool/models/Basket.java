package com.codecool.models;

import com.codecool.IO;
import com.codecool.dao.BasketDao;
import com.jakewharton.fliptables.FlipTable;

import java.sql.SQLException;
import java.util.ArrayList;
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

    public Basket(int id, int productId, int orderId, int quantity) {

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
                prod.setAmount(amount);
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
        String[][] data = (String[][]) innerData.toArray();
        return data;
    }

    private String[] getTotalExpenses() {
        int totalExpenses = 0;
        for (Product product : products) {
            totalExpenses += (product.getPrice() * product.getAmount());
        }
        return new String[]{"Total Expenses: " + totalExpenses};
    }
}
