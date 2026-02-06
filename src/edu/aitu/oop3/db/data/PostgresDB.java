package edu.aitu.oop3.db.data;

import java.sql.Connection;
import java.sql.SQLException;

public class PostgresDB {

    public Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance().getConnection();
    }
}