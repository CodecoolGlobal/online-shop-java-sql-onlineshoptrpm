package com.codecool;

import com.codecool.dao.UserDao;
import com.codecool.models.User;
import java.util.Scanner;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UI ui = new UI();
        UserDao userDao = new UserDao();
        //List<User> users = userDao.getUsers();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Login: ");
        String login = scanner.nextLine();
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();

        User user = userDao.getUser(login, password);
        switch (user.getRole()) {
            case 1:
                ui.displayAdminMenu();
                break;
            case 2:
                ui.displayCustomerMenu();
                break;
        }

        System.out.println(user.getName() + " " + user.getSurname());

    }
}
