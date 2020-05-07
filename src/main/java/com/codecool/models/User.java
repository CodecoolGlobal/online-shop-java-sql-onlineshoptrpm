package com.codecool.models;

import java.text.AttributedString;

import com.codecool.IO;
import com.codecool.dao.ProductDao;

import java.text.AttributedString;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private String password;
    private String email;
    private int phoneNumber;
    private int role;
    private Basket basket;
    private IO io = new IO();

    public User(int id, String name, String password, String email, int phoneNumber, int role) throws SQLException {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.basket = new Basket(0, new ArrayList<>());
        this.basket.setId(basket.getOrderID());
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void addProductToBasket(){
        System.out.println("You are adding product to basket");
        ProductDao productDao = new ProductDao();
        productDao.showAvailableProducts();
        List<Product> products = productDao.getProducts();
        int productID = io.gatherIntInput("Enter id of product:",1, 99999); //todo zmienic max range na dostepna w sklepie

        int indexDifference = 1;
        int id = productID - indexDifference;
        Product product = products.get(id);
        int amount = io.gatherIntInput("Enter amount of product: ",1,9999); //todo max range zmienic na max dostepna w sklepie
        this.getBasket().addProduct(product,amount);
        //added to test
        this.getBasket().seeAllProductsInBasket();
    }

    public void removeProductFromBasket(){
        if (this.getBasket().getProducts().size() == 0 ) {
            System.out.println("Sorry your Basket is empty.");
            return;
        }
        System.out.println("You are removing product from basket");
        this.getBasket().seeAllProductsInBasket();
        int productID = io.gatherIntInput("Enter id of product:",1, this.getBasket().getProducts().size()); //
        int indexDifference = 1;
        int id = productID - indexDifference;
        Product product = this.getBasket().getProducts().get(id);
        this.getBasket().deleteProduct(product);
    }

    public void editProductQuantity(){
        if (this.getBasket().getProducts().size() == 0 ) {
            System.out.println("Sorry your Basket is empty. Nothing to edit");
            return;
        }
        System.out.println("You are editing product quantity in basket");
        this.getBasket().seeAllProductsInBasket();
        int productID = io.gatherIntInput("Enter id of product you want to change quantity:",1, this.getBasket().getProducts().size()); //
        int indexDifference = 1;
        int id = productID - indexDifference;
        Product product = this.getBasket().getProducts().get(id);
        int amount = io.gatherIntInput("Enter new amount of product: ",1,9999); //todo max range zmienic na max dostepna w sklepie
        this.getBasket().setProductQuantity(product, amount);
    }
}
