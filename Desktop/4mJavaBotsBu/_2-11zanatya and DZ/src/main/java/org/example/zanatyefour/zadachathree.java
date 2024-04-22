package org.example.zanatyefour;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class zadachathree {
    private static final Map<String, String> numberWordsMap = new HashMap<>();

    static {
        numberWordsMap.put("1", "один");
        numberWordsMap.put("2", "два");
        numberWordsMap.put("3", "три");
        numberWordsMap.put("4", "четыре");
        numberWordsMap.put("5", "пять");
        numberWordsMap.put("6", "шесть");
        numberWordsMap.put("7", "семь");
        numberWordsMap.put("8", "восемь");
        numberWordsMap.put("9", "девять");
        numberWordsMap.put("10", "десять");
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();

            String replacedContent = replaceNumbersWithWords(content.toString());

            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            writer.write(replacedContent);
            writer.close();

            System.out.println("Замена завершена. Результат записан в файл output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String replaceNumbersWithWords(String text) {
        for (Map.Entry<String, String> entry : numberWordsMap.entrySet()) {
            text = text.replaceAll("\\b" + entry.getKey() + "\\b", entry.getValue());
        }
        return text;
    }
}
