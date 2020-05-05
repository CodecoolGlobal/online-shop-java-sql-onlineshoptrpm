package com.codecool;

import com.codecool.dao.Connector;
import com.codecool.dao.Dao;
import com.codecool.dao.UsersDaoImpl;
import com.codecool.models.User;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Test");
        UsersDaoImpl dao = new UsersDaoImpl();
        List<User> users = dao.getUserData();
        for (User user: users) {
            System.out.println(+ user.getId() + " " + user.getName() + " " + user.getPassword() + " " + user.getUserType());
        }

    }
}
