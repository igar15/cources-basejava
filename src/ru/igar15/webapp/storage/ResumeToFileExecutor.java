package ru.igar15.webapp.storage;

import ru.igar15.webapp.model.Resume;

import java.io.*;

public interface ResumeToFileExecutor {
    void doSave(Resume resume, OutputStream outputStream) throws IOException;

    Resume doRead(String uuid, InputStream inputStream) throws IOException;
}
