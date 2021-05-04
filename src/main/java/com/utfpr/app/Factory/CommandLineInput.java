package com.utfpr.app.Factory;

import java.util.StringTokenizer;

public class CommandLineInput {

    private String db_server;
    private String user;
    private String password;
    private String db_name;
    private String table;

    private CommandLineInput(String db_server, String user, String password, String db_name, String table) {
        this.db_server = db_server;
        this.user = user;
        this.password = password;
        this.db_name = db_name;
        this.table = table;
    }

    public static CommandLineInput build(String argument_list) throws Exception {
        StringTokenizer st = new StringTokenizer(argument_list);

        validateArgumentListSize(st);

        String db_server = st.nextToken();
        String user = st.nextToken();
        String password = st.nextToken();
        String db_name = st.nextToken();

        // There is a fifth element to the argument list
        String table = "";
        if (st.countTokens() == 1) {
            table = st.nextToken();
        }

        return new CommandLineInput(db_server, user, password, db_name, table);
    }

    public String getDbServer() {
        return db_server;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDbName() {
        return db_name;
    }

    public String getTable() {
        return table;
    }

    private static void validateArgumentListSize(StringTokenizer st) throws Exception {
        if (st.countTokens() < 4 || st.countTokens() > 5) {
            throw new Exception("You must provide five arguments to this input (table argument being optional)");
        }
    }

}
