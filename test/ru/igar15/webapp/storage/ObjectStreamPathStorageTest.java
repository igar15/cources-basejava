package ru.igar15.webapp.storage;

import java.nio.file.Path;

import static ru.igar15.webapp.storage.ResumeTestData.RESUME_FILE_STORAGE;

class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new PathStorage(Path.of(RESUME_FILE_STORAGE), new ObjectStreamFileStorage()));
    }
}
