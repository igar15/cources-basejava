package ru.igar15.webapp.exception;

public class NotExistInStorageException extends StorageException {
    public NotExistInStorageException(String uuid) {
        super("Resume with " + uuid + " not exist", uuid);
    }
}
