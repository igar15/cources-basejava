package ru.igar15.webapp.storage;

import ru.igar15.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;
    private static final String RESUME_NOT_FOUND = "ru.igar15.webapp.model.Resume with uuid=%s was not found";

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (r != null) {
            if (getFromStorage(r.getUuid()) != null) {
                System.out.println("ru.igar15.webapp.model.Resume already exists");
            } else if (size == storage.length) {
                System.out.println("Storage is full");
            } else {
                storage[size++] = r;
            }
        }
    }

    public void update(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                storage[i] = r;
                return;
            }
        }
        System.out.println(String.format(RESUME_NOT_FOUND, r.getUuid()));
    }

    public Resume get(String uuid) {
        Resume resume = getFromStorage(uuid);
        if (resume == null) {
            System.out.println(String.format(RESUME_NOT_FOUND, uuid));
        }
        return resume;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                if (size - 1 - i >= 0) System.arraycopy(storage, i + 1, storage, i, size - 1 - i);
                storage[size - 1] = null;
                size--;
                return;
            }
        }
        System.out.println(String.format(RESUME_NOT_FOUND, uuid));
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private Resume getFromStorage(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }
}