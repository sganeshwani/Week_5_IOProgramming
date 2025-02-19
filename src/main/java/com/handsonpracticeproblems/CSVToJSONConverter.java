package com.handsonpracticeproblems;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;

public class CSVToJSONConverter {
    public static void main(String[] args) {
        String inputCSVPath = "C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day05_JSONData\\src\\main\\resources\\input.csv";
        String outputJSONPath = "C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day05_JSONData\\src\\main\\resources\\output.json";

        try (BufferedReader br = new BufferedReader(new FileReader(inputCSVPath))) {
            ObjectMapper objectMapper = new ObjectMapper();
            ArrayNode jsonArray = objectMapper.createArrayNode();

            // Read CSV header
            String headerLine = br.readLine();
            if (headerLine == null) {
                System.err.println("CSV file is empty.");
                return;
            }
            String[] headers = headerLine.split(",");

            // Read CSV data and convert to JSON
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                ObjectNode jsonObject = objectMapper.createObjectNode();
                for (int i = 0; i < headers.length; i++) {
                    jsonObject.put(headers[i], values[i]);
                }
                jsonArray.add(jsonObject);
            }

            // Write JSON output to a file
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputJSONPath), jsonArray);

            System.out.println("CSV successfully converted to JSON and saved to: " + outputJSONPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}