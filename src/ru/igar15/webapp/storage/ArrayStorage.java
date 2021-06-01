package ru.igar15.webapp.storage;

import ru.igar15.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage{
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (r != null) {
            if (getIndex(r.getUuid()) != -1) {
                System.out.println("Resume already exists");
            } else if (size == CAPACITY){
                System.out.println("Storage is full");
            } else {
                storage[size] = r;
                size++;
            }
        }
    }

    public void update(Resume r) {
        if (r != null) {
            int index = getIndex(r.getUuid());
            if (index == -1) {
                System.out.println(String.format(RESUME_NOT_FOUND, r.getUuid()));
            } else {
                storage[index] = r;
            }
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println(String.format(RESUME_NOT_FOUND, uuid));
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}