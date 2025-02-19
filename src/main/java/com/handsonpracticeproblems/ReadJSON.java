package com.handsonpracticeproblems;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class ReadJSON {
    public static void main(String[] args) {
        try {
            // Create ObjectMapper instance and read the JSON file
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(new File("C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day05_JSONData\\src\\main\\resources\\students.json"));

            // Check if root is an object
            if (rootNode.isObject()) {
                // Iterate over each field (key-value pair) in the JSON object
                Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
                while (fields.hasNext()) {
                    Map.Entry<String, JsonNode> entry = fields.next();
                    System.out.println(entry.getKey() + " : " + entry.getValue().toString());
                }
            } else if (rootNode.isArray()) {
                // If the root is an array, print each element in the array
                for (JsonNode node : rootNode) {
                    System.out.println(node.toString());
                }
            } else {
                // For a simple value (string, number, etc.)
                System.out.println(rootNode.asText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

