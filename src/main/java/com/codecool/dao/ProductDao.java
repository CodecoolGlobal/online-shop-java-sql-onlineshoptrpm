package com.codecool.dao;

import com.codecool.IO;
import com.codecool.models.Category;
import com.codecool.models.Product;
import com.jakewharton.fliptables.FlipTable;
import com.jakewharton.fliptables.FlipTableConverters;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        int is_available = results.getInt("is_available");
        int category_id = results.getInt("category_id");
        return new Product(id, name, price, amount, is_available, category_id);
    }

    public void addNewProduct(){
        //temp method
        System.out.println("here will be a method adding new product to database");
    }

    public void showProductsWithRates(){
        String sql = "SELECT id as ID, name as Name, price as Price, amount as Amount, rating as Rating FROM Products WHERE is_available = 1";
        connect();
        try {
            ResultSet rs  = statement.executeQuery(sql);
            System.out.println(FlipTableConverters.fromResultSet(rs));
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showProductsByCategory(){
        CategoryDao c = new CategoryDao();
        System.out.println("Choose category: ");
        for (Category category: c.getCategories())
            System.out.println(category.getId() + " " + category.getName());
        IO io = new IO();
        int choice = io.gatherIntInput("Enter number of category: ",1, c.getCategories().size());
        String sql = "SELECT Products.name as Name\n" +
                "FROM Products\n" +
                "INNER JOIN Categories ON Products.category_id = Categories.id WHERE Categories.id = "+choice+"";
        connect();
        try {
            ResultSet rs  = statement.executeQuery(sql);
            System.out.println(FlipTableConverters.fromResultSet(rs));
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deactivateProduct() {
        connect();
        showAllProducts();
        PreparedStatement rs;
        ProductDao product = new ProductDao();
        IO io = new IO();
        int choiceID = io.gatherIntInput("Enter ID of product: ", 1, product.getProducts().size());
        int choice = io.gatherIntInput("1 - activate product\n0 - deactivate product", 0, 1);
        String sql = "UPDATE Products SET is_available = " + choice + " WHERE id = " + choiceID + "";
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

    public void showAllProducts(){
        String sql = "SELECT * FROM Products";
        connect();
        try {
            ResultSet rs  = statement.executeQuery(sql);
            System.out.println(FlipTableConverters.fromResultSet(rs));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
