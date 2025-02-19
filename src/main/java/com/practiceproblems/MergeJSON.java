package com.practiceproblems;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class MergeJSON {
    public static void main(String[] args) {
        try {
            // Read the JSON files.
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode file1 = objectMapper.readTree(new File("C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day05_JSONData\\src\\main\\resources\\JSONData1.json"));
            JsonNode file2 = objectMapper.readTree(new File("C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day05_JSONData\\src\\main\\resources\\JSONData2.json"));

            // Merge JSON objects.
            ObjectNode mergedJson = objectMapper.createObjectNode();
            mergedJson.setAll((ObjectNode) file1);
            mergedJson.setAll((ObjectNode) file2);

            // Write pretty-printed JSON to a new file
            objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(new File("C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day05_JSONData\\src\\main\\resources\\MergedJSON.json"), mergedJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
