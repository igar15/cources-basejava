package ru.igar15.webapp.storage.serializer;

import ru.igar15.webapp.model.Resume;
import ru.igar15.webapp.util.JsonParser;

import java.io.*;

public class JsonStreamSerializer implements StreamSerializer {
    @Override
    public void doSave(Resume resume, OutputStream outputStream) throws IOException {
        try (Writer writer = new OutputStreamWriter(outputStream)) {
            JsonParser.write(resume, writer);
        }
    }

    @Override
    public Resume doRead(String uuid, InputStream inputStream) throws IOException {
        try (Reader reader = new InputStreamReader(inputStream)) {
            return JsonParser.read(reader, Resume.class);
        }
    }
}
