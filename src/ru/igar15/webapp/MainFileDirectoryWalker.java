package ru.igar15.webapp;

import java.io.File;
import java.io.IOException;

public class MainFileDirectoryWalker {

    public static void printFileNames(File file) {
        if (file.isFile()) {
            System.out.println(file.getName());
        } else {
            File[] files = file.listFiles();
            for (File temp : files) {
                printFileNames(temp);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File projectDirectory = new File("./");
        printFileNames(projectDirectory);
    }
}
