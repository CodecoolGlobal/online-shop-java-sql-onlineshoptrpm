package com.codecool;

import com.jakewharton.fliptables.FlipTable;

import java.util.HashMap;
import java.util.Map;

public class UI {

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void displayAdminMenu(){

    }

    public void displayCustomerMenu(){
        clearScreen();
        Map<Integer, String> customerMenu = makeCustomerChoiceMap();
        System.out.println("Customer MENU: ");
        customerMenu.forEach((k,v) -> System.out.println(k+". "+v));
    }

    private Map<Integer, String> makeCustomerChoiceMap() {
        Map<Integer, String> customerChoiceMap = new HashMap<>();
        customerChoiceMap.put(1, "Show my basket");
        customerChoiceMap.put(2, "Add product to basket");
        customerChoiceMap.put(3, "Remove product from basket");
        customerChoiceMap.put(4, "Edit product's quantity");
        customerChoiceMap.put(5, "Place an order");
        customerChoiceMap.put(6, "Show my previous orders");
        customerChoiceMap.put(7, "Show all available products with rates");
        customerChoiceMap.put(8, "Show products by category");
        customerChoiceMap.put(9, "Check availability of product");
        customerChoiceMap.put(10, "Rate product");
        customerChoiceMap.put(11, "Statistics of orders");
        return customerChoiceMap;
    }
}
