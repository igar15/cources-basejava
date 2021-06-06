package ru.igar15.webapp;

import java.io.File;
import java.io.IOException;

public class MainFileDirectoryWalker {

    public static void printFileNames(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File temp : files) {
                System.out.println(temp.getName());
                if (temp.isDirectory()) {
                    printFileNames(temp);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File projectDirectory = new File("./storage");
        printFileNames(projectDirectory);
    }
}
