package com.advancedproblems;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
        import java.util.Base64;

public class EncryptDecryptCSV {
    private static final String CSV_FILE = "C:\\Users\\HP\\Desktop\\Capgemini Training\\Week5_IOProgramming\\Day01_CSVDataHandling\\src\\main\\resources\\employees.csv";
    private static final String ENCRYPTED_CSV_FILE = "employees_encrypted.csv";
    private static final String SECRET_KEY = "MySecretKey12345"; // 16-char key for AES

    public static void main(String[] args) {
        writeEncryptedCSV();
        readDecryptedCSV();
    }

    // **Method to Encrypt Text Using AES**
    public static String encrypt(String data, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // **Method to Decrypt Text Using AES**
    public static String decrypt(String encryptedData, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }

    // **Method to Write Encrypted CSV**
    public static void writeEncryptedCSV() {
        try (CSVWriter writer = new CSVWriter(new FileWriter(ENCRYPTED_CSV_FILE))) {
            String[] header = {"ID", "Name", "Email", "Salary"};
            writer.writeNext(header);

            // Sample Employee Data (Encrypt Email & Salary)
            String[][] employees = {
                    {"101", "Alice", encrypt("alice@example.com", SECRET_KEY), encrypt("50000", SECRET_KEY)},
                    {"102", "Bob", encrypt("bob@example.com", SECRET_KEY), encrypt("60000", SECRET_KEY)},
                    {"103", "Charlie", encrypt("charlie@example.com", SECRET_KEY), encrypt("55000", SECRET_KEY)}
            };

            for (String[] emp : employees) {
                writer.writeNext(emp);
            }
            System.out.println("Encrypted CSV file created: " + ENCRYPTED_CSV_FILE);
        } catch (Exception e) {
            System.out.println("Error writing encrypted CSV: " + e.getMessage());
        }
    }

    // **Method to Read & Decrypt CSV**
    public static void readDecryptedCSV() {
        try (CSVReader reader = new CSVReader(new FileReader(ENCRYPTED_CSV_FILE))) {
            String[] nextLine;
            reader.readNext(); // Skip header

            System.out.println("\nDecrypted Employee Data:");
            while ((nextLine = reader.readNext()) != null) {
                String decryptedEmail = decrypt(nextLine[2], SECRET_KEY);
                String decryptedSalary = decrypt(nextLine[3], SECRET_KEY);

                System.out.println("ID: " + nextLine[0] +
                        ", Name: " + nextLine[1] +
                        ", Email: " + decryptedEmail +
                        ", Salary: " + decryptedSalary);
            }
        } catch (Exception e) {
            System.out.println("Error reading/decrypting CSV: " + e.getMessage());
        }
    }
}