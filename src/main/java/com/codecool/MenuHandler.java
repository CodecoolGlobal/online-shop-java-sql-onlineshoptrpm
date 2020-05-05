package com.codecool;

import com.codecool.dao.UserDao;
import com.codecool.models.User;

import java.util.HashMap;
import java.util.Map;

public class MenuHandler {
    public boolean isRunning;
    public Map<Integer, Runnable> mainMenu;
    public String[] mainMenuList;
    private UI ui;
    private IO io;
    private UserDao userDao;

    public MenuHandler(){
        this.userDao = new UserDao();
        this.isRunning = true;
        this.ui = new UI();
        this.io = new IO();
        initializeMainMenu();
    }

    private void initializeMainMenu() {
        mainMenuList = new String[] {"1. Create Account", "2. Login", "3. Exit"};
        mainMenu = new HashMap<>();
        mainMenu.put(1, () -> createNewUser());
        mainMenu.put(2, () -> login());
        mainMenu.put(3, () -> exit());
    }

    public void mainMenu(){
        ui.displayMainMenu();
        ui.displayInLine(mainMenuList);
        int userChoice = io.gatherIntInput("\nEnter a number: ", 3);
        mainMenu.get(userChoice).run();
    }

    private void createNewUser() {
        String name = io.gatherInput("Enter your name: ");
        String email = io.gatherInput("Enter your email: "); //todo add double entering email and password for checking correctness
        String password = io.gatherInput("Enter your password: "); //todo cover password in console with "*"
        String phone = String.valueOf(io.gatherIntInput("Enter your phone number: ",999999999));
        String role = "2"; //default for customer
        userDao.addUser(name, email, password, phone, role);
    }

    private void login(){
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
    }

    private void exit(){
        isRunning = false;
    }
}
