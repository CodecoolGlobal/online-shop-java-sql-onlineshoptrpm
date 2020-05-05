package com.codecool.dao;

import com.codecool.models.User;

import java.util.List;


public interface UsersDao {

    public List<User> getUserData();
    public User getUser(int id);
    public void updateUser(int userId, String columnName, String newUpdate);
    public void deleteUser(int userId);
    public void addUser(String name, String password, String userType);
}
