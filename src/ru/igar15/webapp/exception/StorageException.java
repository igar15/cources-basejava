package ru.igar15.webapp.exception;

public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public StorageException(String message) {
        super(message);
        uuid = "";
    }

    public StorageException(Exception e) {
        this(e.getMessage());
    }

    public String getUuid() {
        return uuid;
    }
}
