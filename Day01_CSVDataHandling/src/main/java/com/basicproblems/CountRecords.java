package com.basicproblems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CountRecords {

    public static void main(String[] args) {
        // Path to the CSV file
        String csvFile = "C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day01_CSVDataHandling\\src\\main\\resources\\employees.csv";
        int rowCount = 0;

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            List<String[]> records = reader.readAll(); // Read all records
            rowCount = records.size() - 1; // Subtract 1 to exclude the header row
        } catch (IOException | CsvException e) {
            e.printStackTrace(); // Handle exceptions
        }

        System.out.println("Number of records (excluding the header row): " + rowCount);
    }
}