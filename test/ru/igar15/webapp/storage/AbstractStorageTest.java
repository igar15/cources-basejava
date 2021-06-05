package ru.igar15.webapp.storage;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.igar15.webapp.exception.ExistInStorageException;
import ru.igar15.webapp.exception.NotExistInStorageException;
import ru.igar15.webapp.exception.StorageException;
import ru.igar15.webapp.model.Resume;

import static org.junit.jupiter.api.Assertions.*;
import static ru.igar15.webapp.storage.ResumeTestData.*;

abstract class AbstractStorageTest {
    protected final Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void storageSetup() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
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
        storage.save(RESUME_NEW);
        assertEquals(4, storage.size());
        assertEquals(RESUME_NEW, storage.get(NEW_UUID));
    }

    @Test
    void duplicateSave() {
        assertThrows(ExistInStorageException.class, () -> storage.save(RESUME_1));
    }

    @Test
    void saveFull() {
        Assumptions.assumeTrue(storage instanceof AbstractArrayStorage);
        assertThrows(StorageException.class, () -> {
            for (int i = 0; i <= AbstractArrayStorage.CAPACITY; i++) {
                storage.save(new Resume("vasya"));
            }
        });
    }

    @Test
    void update() {
        storage.update(RESUME_1);
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test
    void updateNotFound() {
        assertThrows(NotExistInStorageException.class, () -> storage.update(RESUME_NOT_EXISTED));
    }


    @Test
    void delete() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
        assertThrows(NotExistInStorageException.class, () -> storage.get(UUID_1));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotExistInStorageException.class, () -> storage.delete(NOT_EXISTED_UUID));
    }

    @Test
    void get() {
        Resume resume = storage.get(UUID_1);
        assertEquals(RESUME_1, resume);
    }

    @Test
    void getNotFound() {
        assertThrows(NotExistInStorageException.class, () -> storage.get(NOT_EXISTED_UUID));
    }

    @Test
    void getAll() {
        Resume[] expected = {RESUME_1, RESUME_2, RESUME_3};
        assertArrayEquals(expected, storage.getAll());
    }
}