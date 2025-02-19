package com.practiceproblems;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadExtractJSON {
    public static void main(String[] args) {
        // Path to the JSON file
        String filePath = "C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day05_JSONData\\src\\main\\resources\\user.json";

        // StringBuilder to hold the content of the JSON file
        StringBuilder jsonContent = new StringBuilder();

        // Reading the JSON file.
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }

        // Creating a JSONArray from the file content
        JSONArray jsonObjects = new JSONArray(jsonContent.toString());

        // Iterating through the JSONArray to extract specific fields
        for (int i = 0; i < jsonObjects.length(); i++) {
            JSONObject object = jsonObjects.getJSONObject(i);
            String name = object.getString("name");
            String email = object.getString("email");
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
        }
    }
}