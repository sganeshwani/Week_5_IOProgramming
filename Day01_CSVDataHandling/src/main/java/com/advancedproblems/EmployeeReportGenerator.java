package com.advancedproblems;

import java.sql.*;
import java.io.FileWriter;
import java.io.IOException;

public class EmployeeReportGenerator {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/Office"; // Change DB name
        String username = "root";
        String password = "mySQL123@#";
        String csvFilePath = "employee_report.csv";

        String query = "SELECT employee_id, name, department, salary FROM employees";

        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            FileWriter writer = new FileWriter(csvFilePath)) {

            // Writing CSV Header
            writer.append("Employee ID,Name,Department,Salary\n");

            // Writing Data
            while (rs.next()) {
                writer.append(rs.getInt("employee_id") + ",");
                writer.append(rs.getString("name") + ",");
                writer.append(rs.getString("department") + ",");
                writer.append(rs.getDouble("salary") + "\n");
            }

            System.out.println("CSV report generated successfully: " + csvFilePath);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}