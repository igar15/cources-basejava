package ru.igar15.webapp.storage;

import ru.igar15.webapp.exception.StorageException;
import ru.igar15.webapp.model.Resume;

import java.io.*;

public class ObjectOutputFileStorage extends AbstractFileStorage {
    protected ObjectOutputFileStorage(File directory) {
        super(directory);
    }

    @Override
    protected void doSave(Resume resume, FileOutputStream fileOutputStream) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(resume);
        }
    }

    @Override
    protected Resume doRead(String uuid, FileInputStream fileInputStream) throws IOException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
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
