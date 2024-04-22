package org.example.zanatyefive;
import java.util.*;
import java.io.File;

public class zadachathree {
    public static void main(String[] args) {
        String directoryPath = "C:\\Users\\vyshe\\IdeaProjects\\Test"; // замените на путь к нужной директории
        List<File> fileList = new ArrayList<>();
        getFilesRecursive(new File(directoryPath), fileList);
        sortFilesBySize(fileList);

        //Collections.sort(fileList, Comparator.comparingLong(File::length)); еще один способ сортировки

        for (File file : fileList) {
            System.out.println(file.getName() + " - " + file.length() + " bytes");
        }
    }

    public static void sortFilesBySize(List<File> fileList) {
        for (int i = 0; i < fileList.size()-1; i++) {
            for (int j = 0; j < fileList.size()-1-i; j++) {
                if (fileList.get(j).length() > fileList.get(j+1).length()) {
                    File temp = fileList.get(j);
                    fileList.set(j, fileList.get(j+1));
                    fileList.set(j+1, temp);
                }
            }
        }
    }

    public static void getFilesRecursive(File directory, List<File> fileList) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    getFilesRecursive(file, fileList);
                } else {
                    fileList.add(file);
                }
            }
        }
    }

}
