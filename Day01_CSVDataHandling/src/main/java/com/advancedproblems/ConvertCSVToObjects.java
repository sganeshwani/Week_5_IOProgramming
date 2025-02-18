package com.advancedproblems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Student {
    private String id;
    private String name;
    private String age;

    public Student(String id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [ID=" + id + ", Name=" + name + ", Age=" + age + "]";
    }
}

public class ConvertCSVToObjects {
    public static void main(String[] args) {
        String path = "C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day01_CSVDataHandling\\src\\main\\resources\\csvdatatoobjects.csv";
        List<Student> students = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                Student student = new Student(line[0], line[1], line[2]);
                students.add(student);
            }

            // Print all students
            students.forEach(System.out::println);
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}