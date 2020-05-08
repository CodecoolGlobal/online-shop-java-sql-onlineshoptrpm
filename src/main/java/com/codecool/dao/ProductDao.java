package com.codecool.dao;

import com.codecool.models.Basket;
import com.codecool.models.Product;
import com.jakewharton.fliptables.FlipTableConverters;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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

    public Product createProduct(ResultSet results) throws SQLException {
        int id = results.getInt("id");
        String name = results.getString("name");
        float price = results.getFloat("price");
        int amount = results.getInt("amount");
        int is_available = results.getInt("is_available");
        int category_id = results.getInt("category_id");
        return new Product(id, name, price, amount, is_available, category_id);
    }

    public void addNewProduct(Product product) {
        connect();
        PreparedStatement addNewProduct;
        String sql = "INSERT INTO Products (name, price, amount, is_available, category_id, rating, no_rates) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            addNewProduct = connection.prepareStatement(sql);
            addNewProduct.setString(1, product.getName());
            addNewProduct.setFloat(2, product.getPrice());
            addNewProduct.setInt(3, product.getAmount());
            addNewProduct.setInt(4, product.isAvailable());
            addNewProduct.setInt(5, product.getCategory());
            addNewProduct.setInt(6, 0);
            addNewProduct.setInt(7, 0);
            addNewProduct.executeUpdate();
            addNewProduct.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showProductsWithRates() {
        String sql = "SELECT id as ID, name as Name, price as Price, amount as Amount, rating as Rating FROM Products WHERE is_available = 1";
        connect();
        try {
            ResultSet rs = statement.executeQuery(sql);
            System.out.println(FlipTableConverters.fromResultSet(rs));
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showProductsByCategory(int choice) {
        String sql = "SELECT Products.name as Name\n" +
                "FROM Products\n" +
                "INNER JOIN Categories ON Products.category_id = Categories.id WHERE Categories.id = " + choice + "";
        connect();
        try {
            ResultSet rs = statement.executeQuery(sql);
            System.out.println(FlipTableConverters.fromResultSet(rs));
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deactivateProduct(int choiceID, int choice) {
        PreparedStatement rs;
        String sql = "UPDATE Products SET is_available = " + choice + " WHERE id = " + choiceID + "";
        connect();
        try {
            rs = connection.prepareStatement(sql);
            rs.executeUpdate();
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showAllProducts() {
        String sql = "SELECT * FROM Products";
        connect();
        try {
            ResultSet rs = statement.executeQuery(sql);
            System.out.println(FlipTableConverters.fromResultSet(rs));
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void editProduct(Product product) {
        PreparedStatement editProduct;
        connect();
        String sql = "UPDATE Products SET name = ?, price = ?, amount = ? WHERE id = ?";
        try {
            editProduct = connection.prepareStatement(sql);
            editProduct.setString(1, product.getName());
            editProduct.setFloat(2, product.getPrice());
            editProduct.setInt(3, product.getAmount());
            editProduct.setInt(4, product.getId());
            editProduct.executeUpdate();
            editProduct.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAvailableProducts() {
        String sql = "SELECT p.id,p.name,p.price,p.amount,p.rating, c.name FROM Categories c\n" +
                "JOIN Products p ON p.category_id = c.id\n" +
                "where p.is_available =1";
        connect();
        try {
            ResultSet rs = statement.executeQuery(sql);
            System.out.println(FlipTableConverters.fromResultSet(rs));
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateProducts(Basket basket) throws SQLException {
        Iterator<Product> basketIterator = basket.getIterator();
        connect();
        PreparedStatement insertToBaskets;
        String insertOrderString = "UPDATE Products SET amount = amount - ? WHERE id = ?";
        while (basketIterator.hasNext()) {
            try {
                insertToBaskets = connection.prepareStatement(insertOrderString);
                Product tempProduct = basketIterator.next();
                insertToBaskets.setInt(1, tempProduct.getAmount());
                insertToBaskets.setInt(2, tempProduct.getId());
                insertToBaskets.executeUpdate();
                insertToBaskets.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        connection.close();
    }

}
