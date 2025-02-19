package com.handsonpracticeproblems;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

public class IPLCensorAnalyzer {

    public static void main(String[] args) {
        // Update these paths as needed
        String jsonInputPath = "C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day05_JSONData\\src\\main\\resources\\ipl_data.json";
        String jsonOutputPath = "C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day05_JSONData\\src\\main\\resources\\ipl_data_censored.json";
        String csvInputPath = "C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day05_JSONData\\src\\main\\resources\\ipl_data.csv";
        String csvOutputPath = "C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day05_JSONData\\src\\main\\resources\\ipl_data_censored.csv";

        processJSONFile(jsonInputPath, jsonOutputPath);
        processCSVFile(csvInputPath, csvOutputPath);
    }

    /*
    Processes a JSON file containing IPL match data,
    applies censorship rules, and writes the sanitized data.
    */
    public static void processJSONFile(String inputPath, String outputPath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            // Read JSON array from file
            ArrayNode matches = (ArrayNode) mapper.readTree(new File(inputPath));

            for (int i = 0; i < matches.size(); i++) {
                ObjectNode match = (ObjectNode) matches.get(i);

                // Mask team names in team1, team2, and winner fields
                String team1 = match.get("team1").asText();
                String team2 = match.get("team2").asText();
                String winner = match.get("winner").asText();
                match.put("team1", maskTeamName(team1));
                match.put("team2", maskTeamName(team2));
                match.put("winner", maskTeamName(winner));

                // For the nested "score" object, mask the keys (team names)
                ObjectNode scoreNode = (ObjectNode) match.get("score");
                ObjectNode maskedScore = mapper.createObjectNode();
                Iterator<Map.Entry<String, com.fasterxml.jackson.databind.JsonNode>> fields = scoreNode.fields();
                while (fields.hasNext()) {
                    Map.Entry<String, com.fasterxml.jackson.databind.JsonNode> entry = fields.next();
                    maskedScore.set(maskTeamName(entry.getKey()), entry.getValue());
                }
                match.set("score", maskedScore);

                // Redact player of the match
                match.put("player_of_match", "REDACTED");
            }

            // Write the censored JSON to file (pretty printed)
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(outputPath), matches);
            System.out.println("Censored JSON file generated at: " + outputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    Processes a CSV file containing IPL match data,
    applies censorship rules, and writes the sanitized data.
    */
    public static void processCSVFile(String inputPath, String outputPath) {
        try (CSVReader reader = new CSVReader(new FileReader(inputPath));
            CSVWriter writer = new CSVWriter(new FileWriter(outputPath))) {

            String[] header = reader.readNext();
            if (header != null) {
                writer.writeNext(header);
            }

            // Expected CSV columns:
            // matchid,team1,team2,team1score,team2score,winner,playerofmatch
            String[] line;
            while ((line = reader.readNext()) != null) {
                // Mask team names columns index 1, 2, and 5 and redact player at index 6
                line[1] = maskTeamName(line[1]);
                line[2] = maskTeamName(line[2]);
                line[5] = maskTeamName(line[5]);
                line[6] = "REDACTED";
                writer.writeNext(line);
            }
            System.out.println("Censored CSV file generated at: " + outputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    Masks a team name by keeping the first word and replacing the rest with "***".
    Like, "Mumbai Indians" becomes "Mumbai ***".
    */
    public static String maskTeamName(String teamName) {
        if (teamName == null || teamName.isEmpty()) {
            return teamName;
        }
        String[] parts = teamName.split(" ");
        if (parts.length < 2) {
            return teamName;
        }
        return parts[0] + " ***";
    }
}
