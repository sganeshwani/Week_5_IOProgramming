package com.intermediateproblems;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ModifyCSVFile {
    public static void main(String[] args) {
        // Path to the CSV file
        String filePath = "C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day01_CSVDataHandling\\src\\main\\resources\\employees.csv";

        // List to store updated data
        ArrayList<String[]> updatedData = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            // Read the header line
            updatedData.add(reader.readNext());

            // Read each line of the CSV file
            while ((line = reader.readNext()) != null) {
                // Check if the department is "IT"
                if (line[2].equalsIgnoreCase("IT")) {
                    // Increase the salary by 10% and update the salary field
                    line[3] = Double.toString(Math.floor(Double.parseDouble(line[3]) * 1.1));
                }
                updatedData.add(line);
            }
            System.out.println("Salary updated successfully");
        } catch (IOException | CsvValidationException io) {
            System.out.println(io.getMessage()); // Handle exceptions
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Write all updated data back to the CSV file
            writer.writeAll(updatedData);
        } catch (IOException io) {
            System.out.println(io.getMessage()); // Handle exceptions
        }
    }
}