package ru.igar15.webapp.storage;

import ru.igar15.webapp.storage.serializer.ObjectStreamSerializer;

import java.nio.file.Path;

import static ru.igar15.webapp.storage.ResumeTestData.RESUME_FILE_STORAGE_DIR;

class ObjectStreamSerializerPathStorageTest extends AbstractStorageTest {

    public ObjectStreamSerializerPathStorageTest() {
        super(new PathStorage(Path.of(RESUME_FILE_STORAGE_DIR.getAbsolutePath()), new ObjectStreamSerializer()));
    }
}
