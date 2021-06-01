package ru.igar15.webapp.storage;

import ru.igar15.webapp.model.Resume;

public abstract class AbstractArrayStorage implements Storage{
    protected static final int CAPACITY = 10000;
    protected Resume[] storage = new Resume[CAPACITY];
    protected int size = 0;
    protected static final String RESUME_NOT_FOUND = "Resume with uuid=%s was not found";

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println(String.format(RESUME_NOT_FOUND, uuid));
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);
}
