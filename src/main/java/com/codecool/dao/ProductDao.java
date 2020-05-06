package com.codecool.dao;

import com.codecool.IO;
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

    public void addNewProduct(){
        IO io = new IO();
        System.out.println("You're adding new product to data base");
        String newName = io.gatherInput("Enter name of new product: ");
        int newPrice = io.gatherIntInput("Enter new price of the product: ", 99999);
        int newAmount = io.gatherIntInput("Enter new amount of the product: ", 99999);
        int isNewAvailable = io.gatherIntInput("Is new product available? ", 1);
        int newCategory = io.gatherIntInput("What is category of new product? ", 7);
//        int newRating = 0;
//        int newNoRates = 0;
        connect();
        PreparedStatement addNewProduct;
        String sql = "INSERT INTO Product (id, name, price, amount, is_available, category_id, rating, no_rates) VALUES (?, ?, ?, ?, ?, 0, 0)";
        try {
            addNewProduct = connection.prepareStatement(sql);
            addNewProduct.setString(1,newName);
            addNewProduct.setInt(2,newPrice);
            addNewProduct.setInt(3,newAmount);
            addNewProduct.setInt(4,isNewAvailable);
            addNewProduct.setInt(5,newCategory);
            addNewProduct.setInt(6,0);
            addNewProduct.setInt(7,0);
            addNewProduct.executeUpdate();
            addNewProduct.close();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
