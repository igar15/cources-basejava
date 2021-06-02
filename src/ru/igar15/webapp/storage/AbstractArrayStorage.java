package ru.igar15.webapp.storage;

import ru.igar15.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage{
    protected static final int CAPACITY = 3;
    protected Resume[] storage = new Resume[CAPACITY];
    protected int size = 0;
    protected static final String RESUME_NOT_FOUND = "Resume with uuid=%s was not found";

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
                System.out.println("Resume already exists");
            } else if (size == CAPACITY){
                System.out.println("Storage is full");
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
                System.out.println(String.format(RESUME_NOT_FOUND, r.getUuid()));
            } else {
                storage[index] = r;
            }
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println(String.format(RESUME_NOT_FOUND, uuid));
        } else {
            doDelete(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println(String.format(RESUME_NOT_FOUND, uuid));
            return null;
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
