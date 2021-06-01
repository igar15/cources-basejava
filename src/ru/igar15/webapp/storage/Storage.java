package ru.igar15.webapp.storage;

import ru.igar15.webapp.model.Resume;

public interface Storage {
    void save(Resume resume);

    void update(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    Resume[] getAll();

    void clear();

    int size();
}
