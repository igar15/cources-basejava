package ru.igar15.webapp.storage.serializer;

import ru.igar15.webapp.model.Resume;

import java.io.*;

public interface StreamSerializer {
    void doSave(Resume resume, OutputStream outputStream) throws IOException;

    Resume doRead(String uuid, InputStream inputStream) throws IOException;
}
