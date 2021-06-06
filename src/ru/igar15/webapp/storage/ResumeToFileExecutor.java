package ru.igar15.webapp.storage;

import ru.igar15.webapp.model.Resume;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public interface ResumeToFileExecutor {
    void doSave(Resume resume, FileOutputStream fileOutputStream) throws IOException;

    Resume doRead(String uuid, FileInputStream fileInputStream) throws IOException;
}
