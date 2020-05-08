package com.codecool.dao;

import com.codecool.models.Basket;
import com.codecool.models.OrderStatus;
import com.codecool.models.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BasketDao extends Dao {

    public List<Integer> getAllOrdersID() throws SQLException{
        List<Integer> ordersIDsList = new ArrayList<>();
        connect();
        try {
            ResultSet results = statement.executeQuery("SELECT order_id FROM Baskets;");
            while (results.next()) {
                int orderId = results.getInt("order_id");
                ordersIDsList.add(orderId);
            }
            results.close();
            statement.close();
            connection.close();
            return ordersIDsList;
        } catch (SQLException e) {
            throw new SQLException ();
        }
    }

    public void addBasketToBaskets(Basket basket) throws SQLException {
        Iterator<Product> basketIterator = basket.getIterator();
        connect();
        PreparedStatement insertToBaskets;
        String insertOrderString = "INSERT INTO Baskets"
                + "(product_id, order_id, quantity)"
                + "VALUES (?, ?, ?)";
        while (basketIterator.hasNext()) {
            try {
                insertToBaskets = connection.prepareStatement(insertOrderString);
                Product tempProduct = basketIterator.next();
                insertToBaskets.setInt(1, tempProduct.getId());
                insertToBaskets.setInt(2, basket.getOrderID());
                insertToBaskets.setInt(3, tempProduct.getAmount());

                insertToBaskets.executeUpdate();
                insertToBaskets.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        connection.close();
    }
}
