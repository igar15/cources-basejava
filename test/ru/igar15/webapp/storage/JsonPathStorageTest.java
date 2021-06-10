package ru.igar15.webapp.storage;

import ru.igar15.webapp.storage.serializer.JsonStreamSerializer;

import java.nio.file.Path;

import static ru.igar15.webapp.storage.ResumeTestData.RESUME_FILE_STORAGE_DIR;

public class JsonPathStorageTest  extends AbstractStorageTest {
    public JsonPathStorageTest() {
        super(new PathStorage(Path.of(RESUME_FILE_STORAGE_DIR.getAbsolutePath()), new JsonStreamSerializer()));
    }
}
