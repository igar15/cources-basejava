package ru.igar15.webapp.storage;

import ru.igar15.webapp.exception.ExistInStorageException;
import ru.igar15.webapp.exception.NotExistInStorageException;
import ru.igar15.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage implements Storage {
    private final Map<String, Resume> map = new HashMap<>();

    @Override
    public void save(Resume resume) {
        if (resume != null) {
            if (map.containsKey(resume.getUuid())) {
                throw new ExistInStorageException(resume.getUuid());
            }
            map.put(resume.getUuid(), resume);
        }
    }

    @Override
    public void update(Resume resume) {
        Resume found = map.get(resume.getUuid());
        if (found == null) {
            throw new NotExistInStorageException(resume.getUuid());
        }
        map.put(resume.getUuid(), resume);
    }

    @Override
    public Resume get(String uuid) {
        Resume resume = map.get(uuid);
        if (resume == null) {
            throw new NotExistInStorageException(uuid);
        }
        return resume;
    }

    @Override
    public void delete(String uuid) {
        Resume removed = map.remove(uuid);
        if (removed == null) {
            throw new NotExistInStorageException(uuid);
        }
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[0]);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }
}
