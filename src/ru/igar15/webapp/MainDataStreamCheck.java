package ru.igar15.webapp;

import ru.igar15.webapp.model.*;
import ru.igar15.webapp.storage.PathStorage;
import ru.igar15.webapp.storage.serializer.DataStreamSerializer;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public class MainDataStreamCheck {
    public static final String RESUME_FILE_STORAGE = "C:/projects/basejava/storage";
    public static final Resume RESUME_1 = new Resume("uuid1", "vasya");

    static {
        RESUME_1.addContact(ContactType.EMAIL, "vasya@yandex.ru");
        RESUME_1.addContact(ContactType.PHONE, "12345");
        RESUME_1.addSection(SectionType.OBJECTIVE,  new TextSection("Developer"));
        RESUME_1.addSection(SectionType.ACHIEVEMENT,  new ListSection(List.of("achiv 1", "achiv 2")));
        RESUME_1.addSection(SectionType.EXPERIENCE,  new OrganizationSection(List.of(
                new Organization("org1", "www.org1.ru", List.of(new Organization.Position(LocalDate.of(2010, 5, 1), LocalDate.of(2012, 4, 1), "developer"))),
                new Organization("org2", "www.org2.ru", List.of(new Organization.Position(LocalDate.of(2013, 5, 1), LocalDate.of(2015, 4, 1), "developer")))
        )));
    }

    public static void main(String[] args) {
        PathStorage pathStorage = new PathStorage(Path.of(RESUME_FILE_STORAGE), new DataStreamSerializer());
        pathStorage.save(RESUME_1);
        Resume resume = pathStorage.get("uuid1");
        System.out.println(resume);
    }
}
