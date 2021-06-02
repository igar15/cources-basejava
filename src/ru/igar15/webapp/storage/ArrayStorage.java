package ru.igar15.webapp.storage;

import ru.igar15.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage{
    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doSave(Resume r) {
        storage[size] = r;
    }

    @Override
    protected void doDelete(int index) {
        storage[index] = storage[size - 1];
    }
}