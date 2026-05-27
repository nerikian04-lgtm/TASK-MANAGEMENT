package com.tracker.tracking.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/tracking";

    private static final String USER = "root";

    private static final String PASSWORD = "secrEt_158";

    public static Connection getConnection() {

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

            System.out.println("Database Connected!");

        } catch (SQLException e) {

            System.out.println("Connection Failed!");
            e.printStackTrace();
        }

        return connection;
    }
}