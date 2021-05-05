package com.utfpr.app.Factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionFactory {

    public static Connection createConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/university";
        String user = "root";
        String password = "password";

        return DriverManager.getConnection(url, user, password);
    }

}
