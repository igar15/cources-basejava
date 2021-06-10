package ru.igar15.webapp.storage;

import ru.igar15.webapp.storage.serializer.XmlStreamSerializer;

import java.nio.file.Path;

import static ru.igar15.webapp.storage.ResumeTestData.*;

public class XmlPathStorageTest extends AbstractStorageTest {
    public XmlPathStorageTest() {
        super(new PathStorage(Path.of(RESUME_FILE_STORAGE_DIR.getAbsolutePath()), new XmlStreamSerializer()));
    }
}
