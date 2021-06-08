package ru.igar15.webapp.storage;

import ru.igar15.webapp.storage.serializer.ObjectStreamSerializer;

import java.io.File;

import static ru.igar15.webapp.storage.ResumeTestData.RESUME_FILE_STORAGE;

class ObjectStreamSerializerFileStorageTest extends AbstractStorageTest {
    public ObjectStreamSerializerFileStorageTest() {
        super(new FileStorage(new File(RESUME_FILE_STORAGE), new ObjectStreamSerializer()));
    }
}
