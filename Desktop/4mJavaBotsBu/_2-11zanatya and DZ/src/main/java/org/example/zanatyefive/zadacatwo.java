package org.example.zanatyefive;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class zadacatwo {
    public static void main(String[] args) {
        // Указываем путь к директории, из которой будем копировать файлы
        File sourceDirectory = new File("путь_к_директории_с_файлами");

        // Указываем путь к директории, в которую будем перемещать файлы
        File destinationDirectory = new File("путь_к_директории_назначения");

        // Указываем расширение файлов для копирования
        String fileExtension = ".txt";

        // Получаем список файлов с указанным расширением
        File[] files = sourceDirectory.listFiles((dir, name) -> name.endsWith(fileExtension));

        if (files != null) {
            for (File file : files) {
                // Создаем новый путь для файла в директории назначения
                Path destinationPath = destinationDirectory.toPath().resolve(file.getName());

                try {
                    // Копируем файл в директорию назначения
                    Files.copy(file.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Файл " + file.getName() + " скопирован успешно.");
                } catch (IOException e) {
                    System.out.println("Не удалось скопировать файл " + file.getName() + ".");
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("В указанной директории нет файлов с расширением " + fileExtension + ".");
        }
    }

}
