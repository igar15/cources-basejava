package ru.igar15.webapp.storage;

import ru.igar15.webapp.storage.serializer.ObjectStreamSerializer;

import static ru.igar15.webapp.storage.ResumeTestData.RESUME_FILE_STORAGE_DIR;

class ObjectStreamSerializerFileStorageTest extends AbstractStorageTest {
    public ObjectStreamSerializerFileStorageTest() {
        super(new FileStorage(RESUME_FILE_STORAGE_DIR, new ObjectStreamSerializer()));
    }
}
