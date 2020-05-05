package com.codecool;

import com.codecool.dao.UserDao;
import com.codecool.models.User;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UI ui = new UI();
        IO io = new IO();
        UserDao userDao = new UserDao();
        //List<User> users = userDao.getUsers();

        String email = io.gatherInput("Enter Email: ");
        String password = io.gatherInput("Enter Password: ");


        User user = userDao.getUser(email, password);
        switch (user.getRole()) {
            case 1:
                ui.displayAdminMenu();
                break;
            case 2:
                ui.displayCustomerMenu();
                break;
        }

        System.out.println(user.getName() + " " + user.getEmail() + " " + user.getPhoneNumber());

    }
}
