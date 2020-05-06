package com.codecool.dao;

import com.codecool.models.Basket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasketDao extends Dao {
    public List<Basket> getBaskets() {
        List<Basket> baskets = new ArrayList<>();
        connect();
        try {
            ResultSet results = statement.executeQuery("SELECT * FROM Baskets;");
            while (results.next()) {
                baskets.add(createBasket(results));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return baskets;
    }

    private Basket createBasket(ResultSet results) throws SQLException {
        int id = results.getInt("id");
        int productId = results.getInt("product_is");
        int orderId = results.getInt("order_id");
        int quantity = results.getInt("quantity");
        return new Basket(id, productId, orderId, quantity);
    }
}
