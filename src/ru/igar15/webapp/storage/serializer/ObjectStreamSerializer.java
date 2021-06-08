package ru.igar15.webapp.storage.serializer;

import ru.igar15.webapp.exception.StorageException;
import ru.igar15.webapp.model.Resume;

import java.io.*;

public class ObjectStreamSerializer implements StreamSerializer {

    @Override
    public void doSave(Resume resume, OutputStream outputStream) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(resume);
        }
    }

    @Override
    public Resume doRead(String uuid, InputStream inputStream) throws IOException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            Resume resume = null;
            try {
                resume = (Resume) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                throw new StorageException("Cannot get Resume from file", uuid);
            }
            return resume;
        }
    }
}
