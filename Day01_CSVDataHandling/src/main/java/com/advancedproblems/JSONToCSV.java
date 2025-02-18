package com.advancedproblems;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class JSONToCSV {
    private static final String JSON_FILE = "C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day01_CSVDataHandling\\src\\main\\resources\\Students.json";
    private static final String CSV_FILE = "C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day01_CSVDataHandling\\src\\main\\resources\\jsontocsv.json";
    private static final String CONVERTED_JSON_FILE = "C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day01_CSVDataHandling\\src\\main\\resources\\csvtojson.json";

    public static void main(String[] args) {
        jsonToCsv(JSON_FILE, CSV_FILE);  // Convert JSON to CSV
        csvToJson(CSV_FILE, CONVERTED_JSON_FILE); // Convert CSV to JSON
    }

    // Convert JSON to CSV
    public static void jsonToCsv(String jsonFilePath, String csvFilePath) {
        try (Reader reader = new FileReader(jsonFilePath);
            CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath))) {

            Gson gson = new Gson();
            Type listType = new TypeToken<List<Map<String, String>>>() {}.getType();
            List<Map<String, String>> students = gson.fromJson(reader, listType);

            // Extract headers (keys from the first JSON object)
            Set<String> headers = students.get(0).keySet();
            writer.writeNext(headers.toArray(new String[0]));

            // Write Data Rows
            for (Map<String, String> student : students) {
                writer.writeNext(student.values().toArray(new String[0]));
            }

            System.out.println("JSON converted to CSV: " + csvFilePath);
        } catch (IOException e) {
            System.out.println("Error converting JSON to CSV: " + e.getMessage());
        }
    }

    // Convert CSV to JSON
    public static void csvToJson(String csvFilePath, String jsonFilePath) {
        try (CSVReader csvReader = new CSVReader(new FileReader(csvFilePath));
            FileWriter writer = new FileWriter(jsonFilePath)) {

            String[] headers = csvReader.readNext(); // Read headers
            List<Map<String, String>> students = new ArrayList<>();
            String[] row;

            while ((row = csvReader.readNext()) != null) {
                Map<String, String> student = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    student.put(headers[i], row[i]);
                }
                students.add(student);
            }

            Gson gson = new Gson();
            gson.toJson(students, writer);

            System.out.println("CSV converted to JSON: " + jsonFilePath);
        } catch (IOException | CsvValidationException e) {
            System.out.println("Error converting CSV to JSON: " + e.getMessage());
        }
    }
}
