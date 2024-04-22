package org.example.zanatyefour;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class firsZD {
    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("input.txt");
            FileWriter writer = new FileWriter("output.txt");
            int c;
            while ((c = reader.read()) != -1) {
                if (c == 'а') {
                    c = 'о';
                }
                writer.write(c);
            }
            reader.close();
            writer.close();
            System.out.println("Данные записаны.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
