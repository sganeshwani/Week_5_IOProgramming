package com.handsonpracticeproblems;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class FilterJSONRecords2 {
    public static void main(String[] args) {
        // Path to your JSON file
        File jsonFile = new File("C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day05_JSONData\\src\\main\\resources\\persons.json");

        // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Parse the JSON file into a JsonNode
            JsonNode rootNode = objectMapper.readTree(jsonFile);

            // Check if the root node is an array
            if (rootNode.isArray()) {
                System.out.println("Filtered records (age > 25):");
                // Iterate through each element in the array
                for (JsonNode personNode : rootNode) {
                    // Check if the record has an "age" field and if it's > 25
                    if (personNode.has("age") && personNode.get("age").asInt() > 25) {
                        // Print the filtered JSON record (pretty printed)
                        String prettyPerson = objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(personNode);
                        System.out.println(prettyPerson);
                    }
                }
            } else {
                System.err.println("Expected a JSON array.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}