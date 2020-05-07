package com.codecool;

import com.codecool.dao.CategoryDao;
import com.codecool.dao.ProductDao;
import com.codecool.dao.UserDao;
import com.codecool.models.Category;
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
    private CategoryDao categoryDao;
    private Map<Integer, Runnable> adminMenu;
    private Map<Integer, Runnable> customerMenu;
    private boolean isLogin;

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
        this.categoryDao = new CategoryDao();
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
            User user = new User(0, name, email, password, phone, role);
            userDao.addUser(user);
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
        isLogin = true;
        switch (user.getRole()) {
            case 1:
                initializeAdminMenu(user);
                adminPanel();
                break;
            case 2:
                initializeCustomerMenu(user);
                customerPanel();
                break;
        }
    }

    private void exit() {
        isRunning = false;
    }

    private void initializeAdminMenu(User user) {
        //adminMenuList = new String[] {"1. xxx", "2. xxx", "3. xxx"};
        adminMenu = new HashMap<>();
        adminMenu.put(1, productDao::addNewProduct);
        adminMenu.put(2, productDao::editProduct);
        adminMenu.put(3, productDao::deactivateProduct);
        adminMenu.put(4, categoryDao::addNewCategory);
        adminMenu.put(5, this::getCategoryData);
//        adminMenu.put(6, "Check orders statuses");
//        adminMenu.put(7, "Discount product");
//        adminMenu.put(8, "Check statistics");
        adminMenu.put(9, this::isLogin);
    }

    private void getCategoryData() {
        CategoryDao c = new CategoryDao();
        System.out.println("You are changing product category name");
        int id = io.gatherIntInput("Give category number to change: ",1,c.getCategories().size()); //poprawic max range
        String name = io.gatherInput("Give new name for category: ");
        c.editProductCategory(new Category(id, name));
    }

    private void adminPanel() {
        while(isLogin){
            ui.displayAdminMenu();
            int userChoice = io.gatherIntInput("\nEnter a number: ", 1, 9);
            adminMenu.get(userChoice).run();
        }
    }

    private void initializeCustomerMenu(User user) {
        //customerMenuList = new String[] {"1. xxx", "2. xxx", "3. xxx"};
        customerMenu = new HashMap<>();
        //customerMenu.put(1, this::createNewUser); // left as example

        customerMenu.put(1, user.getBasket()::seeAllProductsInBasket);
        customerMenu.put(2, user::addProductToBasket);
        customerMenu.put(3, user::removeProductFromBasket);
        customerMenu.put(4, user::editProductQuantity);
//        customerMenu.put(5, "Place an order");
//        customerMenu.put(6, "Show my previous orders");
        customerMenu.put(7, productDao::showProductsWithRates);
        customerMenu.put(8, productDao::showProductsByCategory);
//        customerMenu.put(9, "Check availability of product");
//        customerMenu.put(10, "Rate product");
//        customerMenu.put(11, "Statistics of orders");
        customerMenu.put(12, this::isLogin);
    }

    private void customerPanel() {
        while(isLogin) {
            ui.displayCustomerMenu();
            int userChoice = io.gatherIntInput("\nEnter a number: ", 1, 12);
            customerMenu.get(userChoice).run();
        }
    }

    private void isLogin() {
        isLogin = false;
        System.out.println("\nYou will be logged out\n");
    }

}
