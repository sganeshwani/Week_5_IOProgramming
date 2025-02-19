package com.practiceproblems;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class Student{
    String name;
    int age;
    String []subjects;
    Student(String name,int age,String []subjects){
        this.name=name;
        this.age=age;
        this.subjects=subjects;
    }
    public String toString(){
        return "Name: "+name+" , Age: "+age+", Subjects: "+ Arrays.toString(subjects);
    }
}
public class StudentJSONObject {
    public static void main(String[] args) {
        // Path to the JSON file where data will be stored
        String jsonFilePath = "C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day05_JSONData\\src\\main\\resources\\students.json";
        ArrayList<Student> studentList=new ArrayList<>();
        studentList.add(new Student("Sahil",22,new String[]{"Math","Science","Computer","English","Hindi"}));
        studentList.add(new Student("Rahul",21,new String[]{"Math","Science","Computer","English","Hindi"}));
        studentList.add(new Student("Yashika",20,new String[]{"Math","Science","Computer","English","Hindi"}));
        try(FileWriter writer=new FileWriter(jsonFilePath)){
            JSONArray jsonObjects=getJsonObject(studentList);
            writer.write(jsonObjects.toString(3));
        } catch (IOException io){
            System.out.println(io.getMessage());
        }

    }
    private static JSONArray getJsonObject(ArrayList<Student> list){
        JSONArray jsonObjects=new JSONArray();
        for(Student student : list){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("Name: ",student.name);
            jsonObject.put("Age",student.age);
            JSONArray jSubjects=new JSONArray(student.subjects);
            jsonObject.put("Subjects",jSubjects);
            jsonObjects.put(jsonObject);
        }
        return jsonObjects;
    }
}
