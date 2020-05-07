package com.codecool.dao;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.time.LocalDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import com.codecool.models.Order;
import com.codecool.models.OrderStatus;
import com.codecool.models.Product;
import com.codecool.models.User;

public class OrdersDao extends Dao {

//    private Connection connection;
    private List<Order> orders;

    public OrdersDao() {
        orders = new ArrayList<>();
        //addOrderData();
    }

    public List<Order> getOrderData() {
        return orders;
    }

    public void addOrder(User user) throws SQLException {
        connect();
        String insertOrderString = "INSERT INTO Orders\n"
                + "(user_id, created_at, paid_at, status_id)\n"
                + "VALUES (?, ?, ?, ?)";
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            PreparedStatement insertOrder;
            try {
                insertOrder = connection.prepareStatement(insertOrderString);
                insertOrder.setInt(1, user.getId());
                insertOrder.setString(2, date);
                insertOrder.setInt(3, 0);
                insertOrder.setString(4, OrderStatus.PENDING.name());
                insertOrder.executeUpdate();
                insertOrder.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        public void updateOrderStatus ( int orderId, String status){
            connect();
            PreparedStatement updateOrderStatus;
            String updateOrderStatusString = "UPDATE Orders SET Status = ? WHERE OrderID = ?";
            try {
                updateOrderStatus = connection.prepareStatement(updateOrderStatusString);
                updateOrderStatus.setInt(2, orderId);
                updateOrderStatus.setString(1, status);
                updateOrderStatus.executeUpdate();
                updateOrderStatus.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

//    public boolean isValid(int orderId) {
//        for (Order order : orders) {
//            if (order.getOrderId() == orderId) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private int getIncrementedOrderId() {
//        int orderId;
//
//        if (orders.isEmpty()) {
//            orderId = 1;
//        } else {
//            orders.sort(Order::compareTo);
//            orderId = orders.get(orders.size()-1).getOrderId() + 1;
//        }
//        return orderId;
//    }


        private void addOrderData () {
            connect();
            Order order;
            try {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Baskets b, Products p\n" +
                        "WHERE p.id = b.order_id;");
                while (resultSet.next()) {
                    int orderId = resultSet.getInt("id");
                    int productId = resultSet.getInt("product_id");
                    String productName = resultSet.getString("name");
                    int productAmount = resultSet.getInt("quantity");
                    int productAmountPrice = productAmount * resultSet.getInt("price");
                    int userId = resultSet.getInt("user_id");
                    String date = resultSet.getString("date");
                    String status = resultSet.getString("status");

                    order = new Order.Builder()
                            .withOrderId(orderId)
                            .withProductId(productId)
                            .withProductName(productName)
                            .withProductAmount(productAmount)
                            .withProductAmountPrice(productAmountPrice)
                            .withUserId(userId)
                            .withDate(date)
                            .withStatus(status)
                            .build();
                    orders.add(order);
                }
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

}