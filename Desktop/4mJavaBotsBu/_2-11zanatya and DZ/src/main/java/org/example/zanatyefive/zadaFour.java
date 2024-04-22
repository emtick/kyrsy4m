package org.example.zanatyefive;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class zadaFour {
    public static void main(String[] args) {
        Path directory = Paths.get("путь_к_директории");
        long totalSize = calculateDirectorySize(directory);
        System.out.println("Размер директории: " + totalSize + " байт");
    }

    public static long calculateDirectorySize(Path directory) {
        try {
            DirectorySizeVisitor visitor = new DirectorySizeVisitor();
            Files.walkFileTree(directory, visitor);
            return visitor.getTotalSize();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static class DirectorySizeVisitor extends SimpleFileVisitor<Path> {
        private long totalSize = 0;

        public long getTotalSize() {
            return totalSize;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            totalSize += Files.size(file);
            return super.visitFile(file, attrs);
        }
    }

}
