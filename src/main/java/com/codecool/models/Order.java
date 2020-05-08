package com.codecool.models;

public class Order {

    private Integer orderId;
    private int userId;
    private int productId;
    private String productName;
    private int productAmount;
    private int productAmountPrice;
    private int totalPrice;
    private String date;
    private String status;

    public Order() {
    }

    public int getProductAmountPrice() {
        return productAmountPrice;
    }

    public int getProductAmount(){
        return productAmount;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductId(){
        return productId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public int compareTo(Order other) {
        return this.orderId.compareTo(other.orderId);
    }

    public Order setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public Order setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public Order setProductId(int productId) {
        this.productId = productId;
        return this;
    }

    public Order setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Order setProductAmount(int productAmount) {
        this.productAmount = productAmount;
        return this;
    }

    public Order setProductAmountPrice(int productAmountPrice) {
        this.productAmountPrice = productAmountPrice;
        return this;
    }

    public Order setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public Order setDate(String date) {
        this.date = date;
        return this;
    }

    public Order setStatus(String status) {
        this.status = status;
        return this;
    }
}