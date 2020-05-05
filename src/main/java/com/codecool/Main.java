package com.codecool;

import com.codecool.dao.Dao;
import com.codecool.dao.UserDao;
import com.codecool.models.User;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Test");

        UserDao userDao = new UserDao();
        List<User> users = userDao.getUsers();

        for(User user : users) {
            System.out.println(user.getId() + " " + user.getPhoneNumber());
        }
    }
}
