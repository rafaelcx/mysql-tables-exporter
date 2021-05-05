package com.utfpr.app;

import com.utfpr.app.Factory.CommandLineInputFactory;
import com.utfpr.app.Factory.DbConnectionFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("Please enter your arguments here:");
        String argument_list = getArgumentsFromCommandLine();

        CommandLineInputFactory cli_input = CommandLineInputFactory.build(argument_list);
        Connection db_connection = DbConnectionFactory.createConnection();
    }

    private static String getArgumentsFromCommandLine() throws IOException {
        // Enter data using BufferReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Getting data using readLine
        return reader.readLine();
    }

}
