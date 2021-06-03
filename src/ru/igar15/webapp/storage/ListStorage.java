package ru.igar15.webapp.storage;

import ru.igar15.webapp.exception.ExistInStorageException;
import ru.igar15.webapp.exception.NotExistInStorageException;
import ru.igar15.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> list = new ArrayList<>();

    @Override
    public void save(Resume resume) {
        if (resume != null) {
            if (list.contains(resume)) {
                throw new ExistInStorageException(resume.getUuid());
            }
            list.add(resume);
        }
    }

    @Override
    public void update(Resume resume) {
        int i = list.indexOf(resume);
        if (i == -1) {
            throw new NotExistInStorageException(resume.getUuid());
        }
        list.set(i, resume);
    }

    @Override
    public Resume get(String uuid) {
        for (Resume temp : list) {
            if (temp.getUuid().equals(uuid)) {
                return temp;
            }
        }
        throw new NotExistInStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        boolean result = list.removeIf(resume -> resume.getUuid().equals(uuid));
        if (!result) {
            throw new NotExistInStorageException(uuid);
        }
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[0]);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }
}
