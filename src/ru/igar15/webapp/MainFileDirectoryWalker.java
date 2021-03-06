package ru.igar15.webapp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MainFileDirectoryWalker {

    public static void printFileNames(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File temp : files) {
                printWithTabulation(temp, new StringBuilder(temp.getName()).reverse());
                if (temp.isDirectory()) {
                    printFileNames(temp);
                }
            }
        }
    }

    private static void printWithTabulation(File file, StringBuilder stringBuilder) {
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            System.out.println(stringBuilder.reverse().toString());
        } else {
            stringBuilder.append("\t");
            printWithTabulation(parentFile, stringBuilder);
        }
    }

    public static void main(String[] args) throws IOException {
//        File projectDirectory = new File("./");
//        printFileNames(projectDirectory);
        Path path = Path.of("C:/projects/basejava/storage");
        Path uuid1 = path.resolve("uuid1");
        System.out.println(Files.exists(uuid1));
    }
}
