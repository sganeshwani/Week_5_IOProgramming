package com.handsonpracticeproblems;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Person {
    private String name;
    private int age;

    // No-argument constructor (needed by Jackson)
    public Person() {
    }

    // Constructor with fields
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class LIstToJSON2 {
    public static void main(String[] args) {
        // Create a list of Person objects
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Sahil", 22));
        persons.add(new Person("Sunil", 26));
        persons.add(new Person("Ram", 29));

        // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Define the output file
        File outputFile = new File("C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day05_JSONData\\src\\main\\resources\\persons.json");

        try {
            // Write the list to the file as a pretty-printed JSON array
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, persons);
            System.out.println("JSON file created successfully: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}