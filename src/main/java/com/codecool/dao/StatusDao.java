package com.codecool.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StatusDao extends  Dao{

    public Map<Integer,String> getStatusesMap() {
        Map<Integer,String> statuses = new HashMap<>();
        connect();

        try {
            ResultSet results = statement.executeQuery("SELECT * FROM Statuses;");
            while (results.next()) {
                int id = results.getInt("id");
                String status = results.getString("status");

                statuses.put(id, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statuses;
    }
}
