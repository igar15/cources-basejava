package ru.igar15.webapp.exception;

public class ExistInStorageException extends StorageException {
    public ExistInStorageException(String uuid) {
        super("Resume with " + uuid + " already exist", uuid);
    }
}
