package com.intermediateproblems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class SearchEmployee {
    public static void main(String[] args) {
        // Define the file path of the CSV file
        String path = "C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day01_CSVDataHandling\\src\\main\\resources\\employees.csv";

        // Try-with-resources to automatically close the reader after execution
        try (CSVReader reader = new CSVReader(new FileReader(path))) {

            String[] lines;  // Array to store each row from CSV file

            // Loop through each line in the CSV file
            while ((lines = reader.readNext()) != null) {

                // Extract the employee's name from the second column index 1
                String name = lines[1];

                if (name.equalsIgnoreCase("Sahil")) {
                    // Print the found employee details
                    System.out.println("Found employee: " + name + ", belongs to department: " + lines[2] + " with salary of ₹: " + lines[3]);
                    break;  // Exit loop once the employee is found
                }
            }
        }
        catch (IOException | CsvValidationException e) {
            // Handle exceptions related to file reading or CSV parsing
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
    }
}