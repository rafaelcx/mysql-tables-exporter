package com.utfpr.app.Factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionFactory {

    public static Connection createConnection(CommandLineInputFactory command_line_input) throws SQLException {
        String url = "jdbc:mysql://" + command_line_input.getDbServer() + "/" + command_line_input.getDbName();
        return DriverManager.getConnection(url, command_line_input.getUser(), command_line_input.getPassword());
    }

}
