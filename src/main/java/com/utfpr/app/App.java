package com.utfpr.app;

import com.utfpr.app.Csv.CsvHandler;
import com.utfpr.app.Factory.CommandLineInputFactory;
import com.utfpr.app.Factory.DbConnectionFactory;
import com.utfpr.app.Repository.DbCustomStatement;

import java.io.*;
import java.sql.Connection;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("Please enter your arguments here:");
        String argument_list = getArgumentsFromCommandLine();

        CommandLineInputFactory cli_input = CommandLineInputFactory.build(argument_list);
        Connection db_connection = DbConnectionFactory.createConnection(cli_input);

        DbCustomStatement db_custom_conn = new DbCustomStatement(db_connection);
        List<String> column_name_list = db_custom_conn.getColumnNameListFromTable(cli_input.getTable());
        List<String> row_data_list = db_custom_conn.getRowDataListFromTable(cli_input.getTable(), column_name_list);

        File csv_file = CsvHandler.createCsvFile(cli_input.getDbName(), cli_input.getTable());
        CsvHandler.exportDataToCsvFile(csv_file, column_name_list, row_data_list);

        db_connection.close();
    }

    private static String getArgumentsFromCommandLine() throws IOException {
        // Enter data using BufferReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Getting data using readLine
        return reader.readLine();
    }

}
