package ru.igar15.webapp.storage.serializer;

import ru.igar15.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {
    public static final String TEXT_SECTION_MARKER = "textSection";
    public static final String LIST_SECTION_MARKER = "listSection";
    public static final String ORGANIZATION_SECTION_MARKER = "organizationSection";

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

            Map<SectionType, Section> sections = resume.getSections();
            dataOutputStream.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                String name = entry.getKey().name();
                dataOutputStream.writeUTF(name);
                Section section = entry.getValue();
                if (section instanceof TextSection) {
                    dataOutputStream.writeUTF(TEXT_SECTION_MARKER);
                    dataOutputStream.writeUTF(((TextSection) section).getContent());
                } else if (section instanceof ListSection) {
                    dataOutputStream.writeUTF(LIST_SECTION_MARKER);
                    List<String> items = ((ListSection) section).getItems();
                    dataOutputStream.writeInt(items.size());
                    for (String temp : items) {
                        dataOutputStream.writeUTF(temp);
                    }
                } else if (section instanceof OrganizationSection) {
                    dataOutputStream.writeUTF(ORGANIZATION_SECTION_MARKER);
                    List<Organization> organizations = ((OrganizationSection) section).getOrganizations();
                    dataOutputStream.writeInt(organizations.size());
                    for (Organization temp : organizations) {
                        Link homePage = temp.getHomePage();
                        dataOutputStream.writeUTF(homePage.getName());
                        dataOutputStream.writeUTF(homePage.getUrl());
                        List<Organization.Position> positions = temp.getPositions();
                        dataOutputStream.writeInt(positions.size());
                        for (Organization.Position positionTemp : positions) {
                            dataOutputStream.writeUTF(positionTemp.getTitle());
                            dataOutputStream.writeUTF(positionTemp.getDescription());
                            dataOutputStream.writeUTF(positionTemp.getStartDate().toString());
                            dataOutputStream.writeUTF(positionTemp.getEndDate().toString());
                        }
                    }
                }
            }
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

            int sectionsSize = dataInputStream.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                String s = dataInputStream.readUTF();
                SectionType sectionType = SectionType.valueOf(s);
                Section section = null;
                String sectionMarker = dataInputStream.readUTF();
                if (TEXT_SECTION_MARKER.equals(sectionMarker)) {
                    String content = dataInputStream.readUTF();
                    section = new TextSection(content);
                } else if (LIST_SECTION_MARKER.equals(sectionMarker)) {
                    int itemsSize = dataInputStream.readInt();
                    List<String> items = new ArrayList<>();
                    for (int j = 0; j < itemsSize; j++) {
                        String content = dataInputStream.readUTF();
                        items.add(content);
                    }
                    section = new ListSection(items);
                } else if (ORGANIZATION_SECTION_MARKER.equals(sectionMarker)) {
                    int organizationsSize = dataInputStream.readInt();
                    List<Organization> organizations = new ArrayList<>();
                    for (int j = 0; j < organizationsSize; j++) {
                        String name = dataInputStream.readUTF();
                        String url = dataInputStream.readUTF();
                        int positionsSize = dataInputStream.readInt();
                        List<Organization.Position> positions = new ArrayList<>();
                        for (int k = 0; k < positionsSize; k++) {
                            String title = dataInputStream.readUTF();
                            String description = dataInputStream.readUTF();
                            LocalDate startDate = LocalDate.parse(dataInputStream.readUTF());
                            LocalDate endDate = LocalDate.parse(dataInputStream.readUTF());
                            Organization.Position position = new Organization.Position(startDate, endDate, title, description);
                            positions.add(position);
                        }
                        Organization organization = new Organization(name, url, positions);
                        organizations.add(organization);
                    }
                    section = new OrganizationSection(organizations);
                }
                resume.addSection(sectionType, section);
            }
            return resume;
        }
    }
}
