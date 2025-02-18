package com.basicproblems;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
public class WriteCSV {
    public static void main(String[] args) {
        // Create a CSVWriter object to write data to the CSV file
        try (CSVWriter writer = new CSVWriter(new FileWriter("C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day01_CSVDataHandling\\src\\main\\resources\\employees.csv"))) {
            String[] header = {"ID", "Name", "Department", "Salary"}; // header
            String[] emp1 = {"104", "Alice Williams", "Finance", "60000"};
            String[] emp2 = {"105", "Bob Johnson", "Sales", "58000"};
            String[] emp3 = {"101", "Sahil", "IT", "62000"};
            String[] emp4 = {"103", "Ayush", "HR", "45000"};
            String[] emp5 = {"102", "Riyansh", "IT", "55000"};
            writer.writeNext(header);
            writer.writeNext(emp1);
            writer.writeNext(emp2);
            writer.writeNext(emp3);
            writer.writeNext(emp4);
            writer.writeNext(emp5);
            System.out.println("CSV file written successfully using OpenCSV!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}