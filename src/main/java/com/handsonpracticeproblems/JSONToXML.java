package com.handsonpracticeproblems;

import org.json.JSONObject;
import org.json.XML;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONToXML {

    public static void main(String[] args) {
        try {
            // Read JSON file content as a String
            String jsonFilePath = "C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day05_JSONData\\src\\main\\resources\\car.json"; // update path accordingly
            String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)), StandardCharsets.UTF_8);

            // Convert JSON String to JSONObject
            JSONObject jsonObject = new JSONObject(jsonContent);

            // Convert JSONObject to XML String
            String xmlContent = XML.toString(jsonObject);

            // Optionally, add a root element if needed:
            //xmlContent = "<root>" + xmlContent + "</root>";

            // Write the XML String to a file
            String xmlFilePath = "C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day05_JSONData\\src\\main\\resources\\output.xml"; // update path accordingly
            Files.write(Paths.get(xmlFilePath), xmlContent.getBytes(StandardCharsets.UTF_8));

            System.out.println("XML conversion completed. Check the file: " + xmlFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}