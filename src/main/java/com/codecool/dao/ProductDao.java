package com.codecool.dao;

import com.codecool.models.Category;
import com.codecool.models.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao extends Dao {

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        connect();
        try {
            ResultSet results = statement.executeQuery("SELECT * FROM Products;");
            while (results.next()) {
                products.add(createProduct(results));
            }
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    private Product createProduct(ResultSet results) throws SQLException {
        int id = results.getInt("id");
        String name = results.getString("name");
        float price = results.getFloat("price");
        int amount = results.getInt("amount");
        boolean is_available = results.getInt("is_available") == 1;
        int category_id = results.getInt("category_id");
        return new Product(id, name, price, amount, is_available, category_id);
    }



}
