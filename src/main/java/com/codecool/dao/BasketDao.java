package com.codecool.dao;

import com.codecool.models.Basket;
import com.codecool.models.Product;
import com.codecool.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasketDao extends Dao {

    private ProductDao productDao;

    public BasketDao(){
        this.productDao = new ProductDao();
    }


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

    public Basket getBasket(int order_id) throws SQLException{
        List<Product> productsInBasket = new ArrayList<>();
        connect();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Baskets WHERE order_id = ?;");
            statement.setInt(1, order_id);
            ResultSet results = statement.executeQuery();
            //List<Product> products = productDao.getProducts();
            while (results.next()) {
                int quantity = results.getInt("quantity");
                Product product = productDao.createProduct(results);
                product.setAmount(quantity);
                productsInBasket.add(product);
            }
            Basket basket = new Basket(order_id, productsInBasket);
            results.close();
            statement.close();
            connection.close();
            return basket;
        } catch (SQLException e) {
            throw new SQLException ();
        }
    }

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
}
