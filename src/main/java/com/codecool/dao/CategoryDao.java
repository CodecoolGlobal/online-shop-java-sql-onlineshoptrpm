package com.codecool.dao;

import com.codecool.IO;
import com.codecool.models.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao extends Dao {
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        connect();
        try {
            ResultSet results = statement.executeQuery("SELECT * FROM Categories;");
            while (results.next()) {
                categories.add(createCategory(results));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    private Category createCategory(ResultSet results) throws  SQLException {
        int id = results.getInt("id");
        String name = results.getString("name");
        boolean isAvailable = results.getInt("is_available") == 1;
        return new Category(id, name, isAvailable);
    }

    public void addNewCategory() {
        IO io = new IO();
        System.out.println("You're adding new category to database");
        String newCategory = io.gatherInput("Enter name of new category: ");
        int isNewCatAvailable = io.gatherIntInput("Is new category available?: ",0,1);
        connect();
        PreparedStatement addNewCategory;
        String sql = "INSERT INTO Categories(name, is_available) VALUES (?, ?)";
        try {
            addNewCategory = connection.prepareStatement(sql);
            addNewCategory.setString(1,newCategory);
            addNewCategory.setInt(2,isNewCatAvailable);
            addNewCategory.executeUpdate();
            addNewCategory.close();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editProductCategory() {
        IO io = new IO();
        CategoryDao c = new CategoryDao();
        System.out.println("You are changing product category name");
        int categoryID = io.gatherIntInput("Give category number to change: ",1,c.getCategories().size()); //poprawic max range
        String newCategoryName = io.gatherInput("Give new name for category: ");
        connect();
        PreparedStatement editProductCategory;
        String sql = "UPDATE Categories SET name = ? WHERE id = ?";
        try {
            editProductCategory = connection.prepareStatement(sql);
            editProductCategory.setString(1,newCategoryName);
            editProductCategory.setInt(2,categoryID);
            editProductCategory.executeUpdate();
            editProductCategory.close();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
