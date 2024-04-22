package org.example.zanatyefour;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class zadachatwo {
    public static void main(String[] args) {
        String inputFile = "input.txt"; // путь к входному файлу
        String outputFile = "output.txt"; // путь к выходному файлу

        List<String> words = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineWords = line.split(" ");
                Collections.addAll(words, lineWords);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.reverse(words);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (String word : words) {
                writer.write(word);
                writer.write(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
