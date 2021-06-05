package ru.igar15.webapp.storage;

import ru.igar15.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.igar15.webapp.storage.ResumeTestData.*;

class MapStorageTest extends AbstractStorageTest {

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Override
    void getAll() {
        Resume[] resumes = storage.getAll();
        assertEquals(3, resumes.length);
        List<Resume> resumeList = Arrays.asList(resumes);
        assertTrue(resumeList.contains(RESUME_1));
        assertTrue(resumeList.contains(RESUME_2));
        assertTrue(resumeList.contains(RESUME_3));
    }
}
