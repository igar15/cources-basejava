package ru.igar15.webapp.storage;

import java.io.File;

class ObjectOutputFileStorageTest extends AbstractStorageTest {
    public ObjectOutputFileStorageTest() {
        super(new ObjectOutputFileStorage(new File("./storage")));
    }
}
