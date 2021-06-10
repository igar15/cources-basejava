package ru.igar15.webapp.storage;

import ru.igar15.webapp.Config;
import ru.igar15.webapp.model.*;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class ResumeTestData {
    public static final String UUID_1 = "uuid11111111111111111111111111111111";
    public static final String UUID_2 = "uuid21111111111111111111111111111111";
    public static final String UUID_3 = "uuid31111111111111111111111111111111";
    public static final String NEW_UUID = "uuid_new1111111111111111111111111111";
    public static final String NOT_EXISTED_UUID = "uuid_not_existed";

    public static final Resume RESUME_1 = new Resume(UUID_1, "vasya");
    public static final Resume RESUME_2 = new Resume(UUID_2, "petya");
    public static final Resume RESUME_3 = new Resume(UUID_3, "kolya");
    public static final Resume RESUME_NEW = new Resume(NEW_UUID, "fedya");
    public static final Resume RESUME_NOT_EXISTED = new Resume(NOT_EXISTED_UUID, "kostya");

    public static final File RESUME_FILE_STORAGE_DIR = Config.get().getStorageDir();

    static {
//        RESUME_1.addContact(ContactType.EMAIL, "vasya@yandex.ru");
//        RESUME_1.addContact(ContactType.PHONE, "12345");
//        RESUME_1.addSection(SectionType.OBJECTIVE,  new TextSection("Developer"));
//        RESUME_1.addSection(SectionType.ACHIEVEMENT,  new ListSection(List.of("achiv 1", "achiv 2")));
//        RESUME_1.addSection(SectionType.EXPERIENCE,  new OrganizationSection(List.of(
//                new Organization("org1", "www.org1.ru", List.of(new Organization.Position(LocalDate.of(2010, 5, 1), LocalDate.of(2012, 4, 1), "developer"))),
//                new Organization("org2", "www.org2.ru", List.of(new Organization.Position(LocalDate.of(2013, 5, 1), LocalDate.of(2015, 4, 1), "developer")))
//        )));
//
//        RESUME_2.addContact(ContactType.EMAIL, "petya@yandex.ru");
//        RESUME_2.addContact(ContactType.PHONE, "32234234");
//        RESUME_2.addSection(SectionType.OBJECTIVE,  new TextSection("Developer2"));
//        RESUME_2.addSection(SectionType.ACHIEVEMENT,  new ListSection(List.of("achiv 12", "achiv 21")));
//        RESUME_2.addSection(SectionType.EXPERIENCE,  new OrganizationSection(List.of(
//                new Organization("org3", "www.org1.ru", List.of(new Organization.Position(LocalDate.of(2011, 5, 1), LocalDate.of(2012, 4, 1), "developer"))),
//                new Organization("org2", "www.org2.ru", List.of(new Organization.Position(LocalDate.of(2014, 5, 1), LocalDate.of(2015, 4, 1), "developer")))
//        )));
//
//        RESUME_3.addContact(ContactType.EMAIL, "kolya@yandex.ru");
//        RESUME_3.addContact(ContactType.PHONE, "4534534");
//        RESUME_3.addSection(SectionType.OBJECTIVE,  new TextSection("Developer3"));
//        RESUME_3.addSection(SectionType.ACHIEVEMENT,  new ListSection(List.of("achiv 12er", "achiv 2231")));
//        RESUME_3.addSection(SectionType.EXPERIENCE,  new OrganizationSection(List.of(
//                new Organization("org1", "www.org1.ru", List.of(new Organization.Position(LocalDate.of(2011, 5, 1), LocalDate.of(2012, 4, 1), "developer"))),
//                new Organization("org2", "www.org2.ru", List.of(new Organization.Position(LocalDate.of(2014, 5, 1), LocalDate.of(2015, 4, 1), "developer")))
//        )));
//
//        RESUME_NEW.addContact(ContactType.EMAIL, "fedya@yandex.ru");
//        RESUME_NEW.addContact(ContactType.PHONE, "3453455");
//        RESUME_NEW.addSection(SectionType.OBJECTIVE,  new TextSection("Developer_new"));
//        RESUME_NEW.addSection(SectionType.ACHIEVEMENT,  new ListSection(List.of("achiv new1", "achiv new2")));
//        RESUME_NEW.addSection(SectionType.EXPERIENCE,  new OrganizationSection(List.of(
//                new Organization("org4", "www.org1.ru", List.of(new Organization.Position(LocalDate.of(2011, 5, 1), LocalDate.of(2012, 4, 1), "developer"))),
//                new Organization("org5", "www.org2.ru", List.of(new Organization.Position(LocalDate.of(2014, 5, 1), LocalDate.of(2015, 4, 1), "developer")))
//        )));
    }
}
