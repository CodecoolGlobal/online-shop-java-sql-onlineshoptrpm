package com.codecool;

import com.codecool.dao.ProductDao;
import com.codecool.dao.UserDao;
import com.codecool.models.User;

import java.util.HashMap;
import java.util.Map;

public class MenuHandler {
    public boolean isRunning;
    private Map<Integer, Runnable> mainMenu;
    private String[] mainMenuList;
    private UI ui;
    private IO io;
    private UserDao userDao;
    private ProductDao productDao;
    private Map<Integer, Runnable> adminMenu;
    private Map<Integer, Runnable> customerMenu;

    public MenuHandler() {
        this.isRunning = true;
        this.ui = new UI();
        this.io = new IO();
        initializeDao();
        initializeMainMenu();
    }

    private void initializeDao(){
        this.userDao = new UserDao();
        this.productDao = new ProductDao();
        //todo add rest of Dao when created
    }

    private void initializeMainMenu() {
        mainMenuList = new String[] {"1. Create Account", "2. Login", "3. Exit"};
        mainMenu = new HashMap<>();
        mainMenu.put(1, this::createNewUser);
        mainMenu.put(2, this::login);
        mainMenu.put(3, this::exit);
    }

    public void mainMenu() {
        ui.displayMainMenu();
        ui.displayInLine(mainMenuList);
        int userChoice = io.gatherIntInput("\nEnter a number: ",1, 3);
        mainMenu.get(userChoice).run();
    }

    private void createNewUser() {
        String name = io.gatherInput("Enter your name: ");
        String email = io.gatherInput("Enter your email: ");
        //todo add double entering email and password for checking correctness and if is already in database
        String password = io.gatherInput("Enter your password: "); //todo cover password in console with "*"
        int phone = io.gatherIntInput("Enter your phone number: ",100000000, 999999999);
        int role = 2; //default for customer
        try {
            userDao.addUser(name, email, password, phone, role);
            io.gatherEmptyInput("Account successfully created!\nPress any key to back to main menu.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void login() {
        ui.clearScreen();
        String email = io.gatherInput("Enter Email: ");
        String password = io.gatherInput("Enter Password: ");
        User user = userDao.getUser(email, password);
        switch (user.getRole()) {
            case 1:
                initializeAdminMenu();
                adminPanel();
                break;
            case 2:
                initializeCustomerMenu();
                customerPanel();
                break;
        }
    }

    private void exit() {
        isRunning = false;
    }

    private void initializeAdminMenu() {
        //adminMenuList = new String[] {"1. xxx", "2. xxx", "3. xxx"};
        adminMenu = new HashMap<>();
        adminMenu.put(1, productDao::addNewProduct);
//        adminMenu.put(2, "edit product");
        adminMenu.put(3, productDao::deactivateProduct);
//        adminMenu.put(4, "Create product category");
//        adminMenu.put(5, "Edit product category");
//        adminMenu.put(6, "Check orders statuses");
//        adminMenu.put(7, "Discount product");
//        adminMenu.put(8, "Check statistics");
//        adminMenu.put(9, "Logout");
    }

    private void adminPanel() {
        ui.displayAdminMenu();
        int userChoice = io.gatherIntInput("\nEnter a number: ", 1, 12);
        adminMenu.get(userChoice).run();
    }

    private void initializeCustomerMenu() {
        //customerMenuList = new String[] {"1. xxx", "2. xxx", "3. xxx"};
        customerMenu = new HashMap<>();
        //customerMenu.put(1, this::createNewUser); // left as example

//        customerMenu.put(1, "Show my basket");
//        customerMenu.put(2, "Add product to basket");
//        customerMenu.put(3, "Remove product from basket");
//        customerMenu.put(4, "Edit product's quantity");
//        customerMenu.put(5, "Place an order");
//        customerMenu.put(6, "Show my previous orders");
        customerMenu.put(7, productDao::showProductsWithRates);
        customerMenu.put(8, productDao::showProductsByCategory);
//        customerMenu.put(9, "Check availability of product");
//        customerMenu.put(10, "Rate product");
//        customerMenu.put(11, "Statistics of orders");
//        customerMenu.put(12, "Logout");
    }

    private void customerPanel() {
        ui.displayCustomerMenu();
        int userChoice = io.gatherIntInput("\nEnter a number: ", 1, 9);
        customerMenu.get(userChoice).run();
    }
}
