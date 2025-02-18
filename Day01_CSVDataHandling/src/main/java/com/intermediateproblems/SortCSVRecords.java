package com.intermediateproblems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SortCSVRecords {
    public static void main(String[] args) {
        // Path to the CSV file
        String filePath = "C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day01_CSVDataHandling\\src\\main\\resources\\employees.csv";

        // List to store all records
        List<String[]> list = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;

            // Read the header row and ignore it
            reader.readNext();

            // Read each line of the CSV file
            while ((line = reader.readNext()) != null) {
                list.add(line); // Add the line to the list
            }
        } catch (IOException | CsvValidationException io) {
            System.out.println(io.getMessage()); // Handle exceptions
        }

        // Sort the list by salary in descending order (assuming salary is in the 4th column)
        list.sort((o1, o2) -> Double.compare(Double.parseDouble(o2[3]), Double.parseDouble(o1[3])));

        // Print the top 5 highest-paid employees
        for (int i = 0; i < 5; i++) {
            System.out.println(Arrays.toString(list.get(i)));
        }
    }
}