package com.utfpr.app.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbCustomStatement {

    private Connection db_connection;

    public DbCustomStatement(Connection db_connection) {
        this.db_connection = db_connection;
    }

    public List<String> getColumnNameListFromTable(String table_name) throws SQLException {
        Statement statement = this.db_connection.createStatement();
        ResultSet column_names = statement.executeQuery("SHOW columns FROM " + table_name);

        List<String> column_name_list = new ArrayList<String>();

        while (column_names.next()) {
            column_name_list.add(column_names.getString("Field"));
        }

        statement.close();
        return column_name_list;
    }

    public List<String> getRowDataListFromTable(String table_name, List<String> column_name_list) throws SQLException {
        Statement statement = this.db_connection.createStatement();
        ResultSet result_set = statement.executeQuery("SELECT * FROM " + table_name);

        List<String> row_data_list = new ArrayList<String>();

        while (result_set.next()) {
            StringBuilder string_builder = new StringBuilder();

            for (int i = 0; i < column_name_list.size(); i++) {
                string_builder.append('"' + result_set.getString(column_name_list.get(i)) + '"');
                string_builder.append(",");
            }

            String row_data = string_builder.toString();

            // Removing trailing comma from the end of the string
            row_data = row_data.length() == 0
                    ? null
                    : (row_data.substring(0, row_data.length() - 1));

            row_data_list.add(row_data);
        }

        statement.close();
        return row_data_list;
    }

}
