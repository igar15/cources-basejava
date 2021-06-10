package ru.igar15.webapp.storage;

import ru.igar15.webapp.exception.ExistInStorageException;
import ru.igar15.webapp.exception.NotExistInStorageException;
import ru.igar15.webapp.exception.StorageException;
import ru.igar15.webapp.model.Resume;
import ru.igar15.webapp.storage.serializer.StreamSerializer;

import java.io.*;
import java.util.Objects;

public class FileStorage implements Storage {
    private final File directory;
    private final StreamSerializer streamSerializer;

    public FileStorage(File directory, StreamSerializer streamSerializer) {
        Objects.requireNonNull(directory, "directory must not be null");
        Objects.requireNonNull(streamSerializer, "resumeToFileExecutor must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
        this.streamSerializer = streamSerializer;
    }

    @Override
    public void save(Resume resume) {
        try {
            File resumeFile = new File(directory.getCanonicalPath() + "\\" + resume.getUuid());
            if (!resumeFile.createNewFile()) {
                throw new ExistInStorageException(resume.getUuid());
            }
            streamSerializer.doSave(resume, new FileOutputStream(resumeFile));
        } catch (IOException e) {
            throw new StorageException("Fail to save in storage", resume.getUuid());
        }
    }

    @Override
    public void update(Resume resume) {
        File resumeFile = checkFileExist(resume.getUuid());
        try {
            streamSerializer.doSave(resume, new FileOutputStream(resumeFile));
        } catch (IOException e) {
            throw new StorageException("Fail to update in storage", resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        File resumeFile = checkFileExist(uuid);
        try {
            return streamSerializer.doRead(uuid, new FileInputStream(resumeFile));
        } catch (IOException e) {
            throw new StorageException("Fail to read from storage", uuid);
        }
    }

    @Override
    public void delete(String uuid) {
        File resumeFile = checkFileExist(uuid);
        resumeFile.delete();
    }

    @Override
    public Resume[] getAll() {
        File[] files = directory.listFiles();
        Resume[] resumes = new Resume[files.length];
        for (int i = 0; i < files.length; i++) {
            resumes[i] = get(files[i].getName());
        }
        return resumes;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        for (File temp : files) {
            temp.delete();
        }
    }

    @Override
    public int size() {
        return directory.list().length;
    }

    private File checkFileExist(String fileName) {
        try {
            File resumeFile = new File(directory.getCanonicalPath() + "\\" + fileName);
            if (!resumeFile.exists()) {
                throw new NotExistInStorageException(fileName);
            }
            return resumeFile;
        } catch (IOException e) {
            throw new StorageException("Directory error", fileName);
        }
    }
}
