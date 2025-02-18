package com.basicproblems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
public class ReadCSV {
    public static void main(String[] args) {
        // Reading CSV file from the given path
        try (CSVReader reader = new CSVReader(new FileReader("C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day01_CSVDataHandling\\src\\main\\resources\\students.csv"))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                System.out.println("ID:" + nextLine[0] +
                        ", Name:" + nextLine[1] +
                        ", Age:" + nextLine[2] +
                        ", Marks:" + nextLine[3]);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
