package ru.igar15.webapp.storage;

import ru.igar15.webapp.exception.ExistInStorageException;
import ru.igar15.webapp.exception.NotExistInStorageException;
import ru.igar15.webapp.exception.StorageException;
import ru.igar15.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int CAPACITY = 10000;
    protected Resume[] storage = new Resume[CAPACITY];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (r != null) {
            if (getIndex(r.getUuid()) >= 0) {
                throw new ExistInStorageException(r.getUuid());
            } else if (size == CAPACITY){
                throw new StorageException("Storage is full", r.getUuid());
            } else {
                doSave(r);
                size++;
            }
        }
    }

    public void update(Resume r) {
        if (r != null) {
            int index = getIndex(r.getUuid());
            if (index < 0) {
                throw new NotExistInStorageException(r.getUuid());
            } else {
                storage[index] = r;
            }
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistInStorageException(uuid);
        } else {
            doDelete(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistInStorageException(uuid);
        }
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void doSave(Resume r);

    protected abstract void doDelete(int index);
}
