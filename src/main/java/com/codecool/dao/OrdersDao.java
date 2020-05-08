package com.codecool.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.codecool.models.Order;
import com.codecool.models.OrderStatus;
import com.codecool.models.User;
import com.jakewharton.fliptables.FlipTableConverters;

public class OrdersDao extends Dao {

    private List<Order> orders;

    public OrdersDao() {
        orders = new ArrayList<>();
        //addOrderData();
    }

    public List<Order> getOrderData() {
        return orders;
    }

    public void addOrder(User user) {
        connect();
        String insertOrderString = "INSERT INTO Orders\n"
                + "(user_id, created_at, paid_at, status_id)\n"
                + "VALUES (?, ?, ?, ?)";

        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
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

    public void showOrders(User user) {
        String sql = "SELECT * FROM Orders\n" +
                "WHERE user_id = " + user.getId()+"";
        connect();
        try {
            ResultSet rs  = statement.executeQuery(sql);
            System.out.println(FlipTableConverters.fromResultSet(rs));
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}