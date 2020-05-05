package com.codecool.dao;

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
                int id = results.getInt("id");
                String name = results.getString("name");
                String price = results.getString("price");
                int amount = results.getInt("amount");
                int is_available = results.getInt("is_available");
                int category_id = results.getInt("category_id");

                Product product = new Product(id, name, price, amount, is_available, category_id);
                products.add(product);
            }
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
