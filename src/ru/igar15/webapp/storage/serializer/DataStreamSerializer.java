package ru.igar15.webapp.storage.serializer;

import ru.igar15.webapp.model.ContactType;
import ru.igar15.webapp.model.Resume;

import java.io.*;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doSave(Resume resume, OutputStream outputStream) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            dataOutputStream.writeUTF(resume.getUuid());
            dataOutputStream.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dataOutputStream.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dataOutputStream.writeUTF(entry.getKey().name());
                dataOutputStream.writeUTF(entry.getValue());
            }
            // TODO implement section
        }
    }

    @Override
    public Resume doRead(String uuid, InputStream inputStream) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(inputStream)) {
            String uuidRead = dataInputStream.readUTF();
            String fullName = dataInputStream.readUTF();
            Resume resume = new Resume(uuidRead, fullName);
            int contactsSize = dataInputStream.readInt();
            for (int i = 0; i < contactsSize; i++) {
                ContactType contactType = ContactType.valueOf(dataInputStream.readUTF());
                String contact = dataInputStream.readUTF();
                resume.addContact(contactType, contact);
            }
            // TODO implement section
            return resume;
        }
    }
}
