package com.advancedproblems;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MergeCSV {
    public static void main(String[] args) {
        String students1Path = "C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day01_CSVDataHandling\\src\\main\\resources\\student1.csv";
        String students2Path = "C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day01_CSVDataHandling\\src\\main\\resources\\student2.csv";
        String outputPath = "C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day01_CSVDataHandling\\src\\main\\resources\\MergedCSV.csv";

        Map<String, String[]> studentsData = new HashMap<>();

        try (CSVReader reader1 = new CSVReader(new FileReader(students1Path))) {
            String[] line;
            while ((line = reader1.readNext()) != null) {
                studentsData.put(line[0], line); // ID as key
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        try (CSVReader reader2 = new CSVReader(new FileReader(students2Path));
             CSVWriter writer = new CSVWriter(new FileWriter(outputPath))) {

            // Write header to output file
            String[] header = {"ID", "Name", "Age", "Marks", "Grade"};
            writer.writeNext(header);

            // Merge records from second file
            String[] line;
            while ((line = reader2.readNext()) != null) {
                String[] student1 = studentsData.get(line[0]);
                if (student1 != null) {
                    // Merge data and write to output CSV
                    String[] merged = new String[]{student1[0], student1[1], student1[2], line[1], line[2]};
                    writer.writeNext(merged);
                }
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
