package com.handsonpracticeproblems;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;

public class ValidateEmail {

    public static void main(String[] args) {
        // Load the JSON Schema from the resources folder
        try (InputStream schemaStream = ValidateEmail.class.getResourceAsStream("/email-schema.json")) {
            if (schemaStream == null) {
                System.err.println("Schema file not found.");
                return;
            }
            JSONObject rawSchema = new JSONObject(new JSONTokener(schemaStream));
            Schema schema = SchemaLoader.load(rawSchema);

            // Load the JSON data (sample file) from the resources folder
            try (InputStream dataStream = ValidateEmail.class.getResourceAsStream("/email.json")) {
                if (dataStream == null) {
                    System.err.println("JSON data file not found.");
                    return;
                }
                JSONObject jsonData = new JSONObject(new JSONTokener(dataStream));

                // Validate the JSON data against the schema
                try {
                    schema.validate(jsonData);
                    System.out.println("JSON is valid!");
                } catch (ValidationException e) {
                    System.err.println("JSON is invalid:");
                    e.getAllMessages().forEach(System.err::println);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}