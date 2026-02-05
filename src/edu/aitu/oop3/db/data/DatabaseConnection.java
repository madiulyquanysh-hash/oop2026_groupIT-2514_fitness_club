package edu.aitu.oop3.db.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static Connection connection = null;
    private static Properties props = new Properties();

    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try (FileInputStream in = new FileInputStream("config.properties")) {
                props.load(in);
                String url = props.getProperty("db.url");
                String user = props.getProperty("db.user");
                String pass = props.getProperty("db.password");
                connection = DriverManager.getConnection(url, user, pass);
            } catch (IOException e) {
                System.err.println("Config file error: " + e.getMessage());
            }
        }
        return connection;
    }
}