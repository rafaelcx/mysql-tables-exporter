package com.utfpr.app;

import com.utfpr.app.Factory.CommandLineInputFactory;
import com.utfpr.app.Factory.DbConnectionFactory;
import com.utfpr.app.Repository.DbCustomStatement;

import java.io.*;
import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("Please enter your arguments here:");
        String argument_list = getArgumentsFromCommandLine();

        CommandLineInputFactory cli_input = CommandLineInputFactory.build(argument_list);
        Connection db_connection = DbConnectionFactory.createConnection(cli_input);

        DbCustomStatement db_custom_conn = new DbCustomStatement(db_connection);
        List<String> column_name_list = db_custom_conn.getColumnNameListFromTable(cli_input.getTable());
        List<String> row_data_list = db_custom_conn.getRowDataListFromTable(cli_input.getTable(), column_name_list);

        // Creating file
        String csv_file_path = System.getProperty("user.home");
        String csv_file_name = cli_input.getDbName() + "-" + cli_input.getTable() + ".csv";
        new File(csv_file_path + "/" + csv_file_name);

        FileWriter csv_writer = new FileWriter(csv_file_path + "/" + csv_file_name);

        // Writing header to file
        csv_writer.append(String.join(",", column_name_list));
        csv_writer.append("\n");

        // Writing rows to file
        csv_writer.append(String.join("\n", row_data_list));

        csv_writer.flush();
        csv_writer.close();
        db_connection.close();
    }

    private static String getArgumentsFromCommandLine() throws IOException {
        // Enter data using BufferReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Getting data using readLine
        return reader.readLine();
    }

    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

}
