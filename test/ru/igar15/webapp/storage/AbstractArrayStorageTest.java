package ru.igar15.webapp.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.igar15.webapp.exception.ExistInStorageException;
import ru.igar15.webapp.exception.NotExistInStorageException;
import ru.igar15.webapp.exception.StorageException;
import ru.igar15.webapp.model.Resume;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String NEW_UUID = "new_uuid";
    private static final String NOT_EXISTED = "xxxx";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void storageSetup() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    void size() {
        assertEquals(3, storage.size());
    }

    @Test
    void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    void save() {
        Resume resume = new Resume(NEW_UUID);
        storage.save(resume);
        assertEquals(4, storage.size());
        assertEquals(resume, storage.get(NEW_UUID));
    }

    @Test
    void duplicateSave() {
        assertThrows(ExistInStorageException.class, () -> storage.save(new Resume(UUID_1)));
    }

    @Test
    void saveFull() throws NoSuchFieldException, IllegalAccessException {
        assertThrows(StorageException.class, () -> {
            for (int i = 0; i <= AbstractArrayStorage.CAPACITY; i++) {
                storage.save(new Resume());
            }
        });
    }

    @Test
    void update() {
        Resume updated = new Resume(UUID_1);
        storage.update(updated);
        assertEquals(updated, storage.get(UUID_1));
    }

    @Test
    void updateNotFound() {
        assertThrows(NotExistInStorageException.class, () -> storage.update( new Resume(NOT_EXISTED)));
    }


    @Test
    void delete() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
        assertThrows(NotExistInStorageException.class, () -> storage.get(UUID_1));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotExistInStorageException.class, () -> storage.delete(NOT_EXISTED));
    }

    @Test
    void get() {
        Resume resume = storage.get(UUID_1);
        assertEquals(UUID_1, resume.getUuid());
    }

    @Test
    void getNotFound() {
        assertThrows(NotExistInStorageException.class, () -> storage.get(NOT_EXISTED));
    }

    @Test
    void getAll() {
        Resume[] expected = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        assertArrayEquals(expected, storage.getAll());
    }
}