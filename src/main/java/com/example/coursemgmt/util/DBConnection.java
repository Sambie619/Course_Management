package com.example.coursemgmt.util;



import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static Connection connection = null;

    // private constructor to prevent instantiation
    private DBConnection() {}

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        }

        try {
            // Load properties file
            Properties props = new Properties();
            InputStream input = DBConnection.class.getClassLoader()
                    .getResourceAsStream("config.properties");
            if (input == null) {
                throw new RuntimeException("Cannot find config.properties file");
            }
            props.load(input);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            // Create connection
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected successfully ✅");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error connecting to the database");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading DB config");
        }

        return connection;
    }
}

