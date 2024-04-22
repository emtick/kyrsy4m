package org.example.zanatyefive;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class zadacc {
    public static void main(String[] args) {
        // Ввод пути к директории
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к директории: ");
        String directoryPath = scanner.nextLine();

        // Проверка введенного пути на валидность
        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            System.out.println("Неправильный путь к директории!");
            return;
        }

        // Ввод расширения файлов
        System.out.print("Введите расширение файлов: ");
        String extension = scanner.nextLine();

        // Обход всех файлов в указанной директории и фильтрация по расширению
        List<File> filteredFiles = filterFilesByExtension(directory, extension);

        // Вывод отфильтрованного списка файлов на экран
        System.out.println("Отфильтрованные файлы:");
        for (File file : filteredFiles) {
            System.out.println(file.getName());
        }
    }

    private static List<File> filterFilesByExtension(File directory, String extension) {
        List<File> filteredFiles = new ArrayList<>();

        // Получение всех файлов в указанной директории
        File[] files = directory.listFiles();

        // Фильтрация файлов по расширению
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(extension)) {
                filteredFiles.add(file);
            }
        }

        return filteredFiles;
    }

}
