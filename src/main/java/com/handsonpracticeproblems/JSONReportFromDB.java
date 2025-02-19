package com.handsonpracticeproblems;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.sql.*;
        import java.util.*;

public class JSONReportFromDB {
    public static void main(String[] args) {
        // Database connection details (update these accordingly)
        String jdbcUrl = "jdbc:mysql://localhost:3306/sampledb";
        String username = "root";
        String password = "mySQL123@#";
        String sql = "SELECT id, name, email, age FROM users";

        // List to hold each row as a map
        List<Map<String, Object>> records = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Get column metadata
            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();

            // Process each row from the result set
            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                for (int i = 1; i <= colCount; i++) {
                    String columnName = meta.getColumnLabel(i);
                    Object columnValue = rs.getObject(i);
                    row.put(columnName, columnValue);
                }
                records.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // Convert the list of records to JSON and print it
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT); // pretty-print JSON
            String jsonReport = mapper.writeValueAsString(records);
            System.out.println("JSON Report:");
            System.out.println(jsonReport);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}