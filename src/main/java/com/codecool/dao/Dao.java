package com.codecool.dao;

import java.sql.*;
import java.util.ArrayList;

public abstract class Dao {
    protected Connection connection;
    protected Statement statement;

    public static final String DB_NAME = "src/main/resources/newShopDB.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

    public ResultSet select(String table, ArrayList<String> columns) throws SQLException {
        String query = "SELECT ? FROM ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, columns.toString());
        preparedStatement.setString(2, table);

        return statement.executeQuery(query);
    }

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(CONNECTION_STRING);
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.getStackTrace();
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database" + e.getMessage());
        }
    }
}
