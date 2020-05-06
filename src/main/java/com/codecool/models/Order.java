package com.codecool.models;


import java.util.Date;

public class Order {
    private int id;
    private Basket basket;
    private User user;
    private Date orderCreatedAt;
    private Date orderPayAt;
    private OrderStatus orderStatus;

    public Order(int id, Basket basket, User user, Date orderCreatedAt, Date orderPayAt, OrderStatus orderStatus) {
        this.id = id;
        this.basket = basket;
        this.user = user;
        this.orderCreatedAt = orderCreatedAt;
        this.orderPayAt = orderPayAt;
        this.orderStatus = orderStatus;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getOrderCreatedAt() {
        return orderCreatedAt;
    }

    public void setOrderCreatedAt(Date orderCreatedAt) {
        this.orderCreatedAt = orderCreatedAt;
    }

    public Date getOrderPayAt() {
        return orderPayAt;
    }

    public void setOrderPayAt(Date orderPayAt) {
        this.orderPayAt = orderPayAt;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
