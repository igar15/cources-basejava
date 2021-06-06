package ru.igar15.webapp.storage;

import java.io.File;

import static ru.igar15.webapp.storage.ResumeTestData.RESUME_FILE_STORAGE;

class ObjectStreamFileStorageTest extends AbstractStorageTest {
    public ObjectStreamFileStorageTest() {
        super(new FileStorage(new File(RESUME_FILE_STORAGE), new ObjectStreamFileStorage()));
    }
}
