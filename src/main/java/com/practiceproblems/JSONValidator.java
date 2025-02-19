package com.practiceproblems;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class JSONValidator {
    public static void main(String[] args) {
        // Create ObjectMapper object to parse JSON
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Read JSON file and parse it
            JsonNode jsonNode = objectMapper.readTree(new File("C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day05_JSONData\\src\\main\\resources\\students.json"));
            System.out.println("JSON is well-formed!");
        } catch (IOException e) {
            System.out.println("Invalid JSON: " + e.getMessage());
        }
    }
}