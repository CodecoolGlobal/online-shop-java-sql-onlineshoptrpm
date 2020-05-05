package com.codecool.dao;

import com.codecool.models.Category;

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
                int id = results.getInt("id");
                String name = results.getString("name");
                boolean isAvailable = results.getInt("is_available") == 1;

                Category user = new Category(id, name, isAvailable);
                categories.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
