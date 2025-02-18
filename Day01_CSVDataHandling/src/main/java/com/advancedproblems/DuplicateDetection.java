package com.advancedproblems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DuplicateDetection {
    public static void main(String[] args) {
        String path = "C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day01_CSVDataHandling\\src\\main\\resources\\employees.csv";
        Set<String> seenIds = new HashSet<>();

        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                String id = line[0]; // Assuming ID is at index 0
                if (!seenIds.add(id)) {
                    System.out.println("Duplicate record found: " + String.join(",", line));
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}