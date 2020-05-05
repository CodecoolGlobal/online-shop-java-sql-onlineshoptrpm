package com.codecool.dao;

import com.codecool.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserDao extends Dao {
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        connect();

        try {
            ResultSet results = statement.executeQuery("SELECT * FROM Users;");
            while (results.next()) {
                int userId = results.getInt("UserID");
                String name = results.getString("Name");
                String password = results.getString("Password");
                String userType = results.getString("UserType");
                int phoneNumber = results.getInt("PhoneNumber");

                User user = new User(userId, name, password, userType, phoneNumber);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserType (String name, String userPassword) {
        connect();
        User user;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE Name = ? and UserType = ?");
            statement.setString(1, name);
            statement.setString(2, userPassword);

            ResultSet results = statement.executeQuery();

            while (results.next()) {
                int userID = results.getInt("UserID");
                String userType = results.getString("UserType");
                int phoneNumber = results.getInt("PhoneNumber");

                user = new User(userID, name, userPassword, userType, phoneNumber);
                return  user;
            }
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new NoSuchElementException("Taki uzytkownik nie istnieje");
    }
}
