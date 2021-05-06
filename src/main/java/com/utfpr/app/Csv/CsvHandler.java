package com.utfpr.app.Csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvHandler {

    public static File createCsvFile(String db_name, String table_name) {
        String csv_file_path = System.getProperty("user.home");
        String csv_file_name = db_name + "-" + table_name + ".csv";
        return new File(csv_file_path + "/" + csv_file_name);
    }

    public static void exportDataToCsvFile(File csv_file, List<String> column_name_list, List<String> row_data_list) throws IOException {
        FileWriter csv_writer = new FileWriter(csv_file.getAbsolutePath());

        // Write header
        csv_writer.append(String.join(",", column_name_list));
        csv_writer.append("\n");

        // Write rows
        csv_writer.append(String.join("\n", row_data_list));

        csv_writer.flush();
        csv_writer.close();
    }

}
