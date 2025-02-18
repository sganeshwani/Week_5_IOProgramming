package com.advancedproblems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVValidator {
    // Method to validate email using regex
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"; // More strict validation
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Method to validate phone number (must contain exactly 10 digits)
    private static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\d{10}");
    }

    public static void main(String[] args) {
        String path = "C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day01_CSVDataHandling\\src\\main\\resources\\emailphoneno.csv";

        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            // Skip header
            reader.readNext();

            String[] line;
            while ((line = reader.readNext()) != null) {
                if (line.length < 2) {
                    System.out.println("Skipping invalid row: " + String.join(",", line));
                    continue; // Skip incomplete rows
                }

                String phone = line[0].replaceAll("\"", ""); // Remove quotes
                String email = line[1].replaceAll("\"", ""); // Remove quotes

                if (!isValidEmail(email)) {
                    System.out.println("Invalid email: " + email);
                }

                if (!isValidPhone(phone)) {
                    System.out.println("Invalid phone number: " + phone);
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}