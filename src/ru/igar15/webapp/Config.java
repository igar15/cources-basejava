package ru.igar15.webapp;

import java.io.*;
import java.util.Properties;

public class Config {
    public static final File PROPS = new File("config/resumes.properties");
    private static final Config INSTANCE = new Config();
    private final Properties props = new Properties();
    private File storageDir;

    public static Config get() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream inputStream = new FileInputStream(PROPS)) {
            props.load(inputStream);
            storageDir = new File(props.getProperty("storage.dir"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }

    public File getStorageDir() {
        return storageDir;
    }
}
