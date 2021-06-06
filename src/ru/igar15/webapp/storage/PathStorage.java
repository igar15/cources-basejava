package ru.igar15.webapp.storage;

import ru.igar15.webapp.exception.ExistInStorageException;
import ru.igar15.webapp.exception.NotExistInStorageException;
import ru.igar15.webapp.exception.StorageException;
import ru.igar15.webapp.model.Resume;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class PathStorage extends AbstractStorage {
    private final Path directory;
    private final ResumeToFileExecutor resumeToFileExecutor;

    public PathStorage(Path directory, ResumeToFileExecutor resumeToFileExecutor) {
        Objects.requireNonNull(directory, "directory must not be null");
        Objects.requireNonNull(resumeToFileExecutor, "resumeToFileExecutor must not be null");
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(directory.toAbsolutePath() + " is not directory");
        }
        if (!Files.isReadable(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(directory.toAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
        this.resumeToFileExecutor = resumeToFileExecutor;
    }

    @Override
    public void save(Resume resume) {
        Path resumeFile = directory.resolve(resume.getUuid());
        if (Files.exists(resumeFile)) {
            throw new ExistInStorageException(resume.getUuid());
        }
        try {
            resumeToFileExecutor.doSave(resume, new FileOutputStream(resumeFile.toFile()));
        } catch (IOException e) {
            throw new StorageException("Fail to save in storage", resume.getUuid());
        }
    }

    @Override
    public void update(Resume resume) {
        Path resumeFile = checkFileExist(resume.getUuid());
        try {
            resumeToFileExecutor.doSave(resume, new FileOutputStream(resumeFile.toFile()));
        } catch (IOException e) {
            throw new StorageException("Fail to update in storage", resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        Path resumeFile = checkFileExist(uuid);
        try {
            return resumeToFileExecutor.doRead(uuid, new FileInputStream(resumeFile.toFile()));
        } catch (IOException e) {
            throw new StorageException("Fail to read from storage", uuid);
        }
    }

    @Override
    public void delete(String uuid) {
        Path resumeFile = checkFileExist(uuid);
        try {
            Files.delete(resumeFile);
        } catch (IOException e) {
            throw new StorageException("Fail to delete from storage", uuid);
        }
    }

    @Override
    public Resume[] getAll() {
        try {
            return Files.list(directory)
                    .map(path -> get(path.getFileName().toString()))
                    .toArray(Resume[]::new);
        } catch (IOException e) {
            throw new StorageException("Fail to get from storage");
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(path -> {
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            throw new StorageException("Fail to clear storage");
        }
    }

    @Override
    public int size() {
        try {
            return (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("Fail to count storage size");
        }
    }

    private Path checkFileExist(String fileName) {
        Path resumeFile = directory.resolve(fileName);
        if (!Files.exists(resumeFile)) {
            throw new NotExistInStorageException(fileName);
        }
        return resumeFile;
    }
}
