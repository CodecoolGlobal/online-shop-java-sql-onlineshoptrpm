package com.codecool.dao;

import com.codecool.models.Category;
import com.jakewharton.fliptables.FlipTableConverters;

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

    public void showAllCategories(){
        String sql = "SELECT id, name FROM Categories";
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

    private Category createCategory(ResultSet results) throws  SQLException {
        int id = results.getInt("id");
        String name = results.getString("name");
        int isAvailable = results.getInt("is_available");
        Category category = new Category(id, name);
        category.setAvailable(isAvailable);
        return category;
    }

    public void addNewCategory(Category category) {
        connect();
        PreparedStatement addNewCategory;
        String sql = "INSERT INTO Categories(name, is_available) VALUES (?, ?)";
        try {
            addNewCategory = connection.prepareStatement(sql);
            addNewCategory.setString(1,category.getName());
            addNewCategory.setInt(2,category.getIsAvailable());
            addNewCategory.executeUpdate();
            addNewCategory.close();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editProductCategory(Category category) {
        connect();
        PreparedStatement editProductCategory;
        String sql = "UPDATE Categories SET name = ? WHERE id = ?";
        try {
            editProductCategory = connection.prepareStatement(sql);
            editProductCategory.setString(1, category.getName());
            editProductCategory.setInt(2, category.getId());
            editProductCategory.executeUpdate();
            editProductCategory.close();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
