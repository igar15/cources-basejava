package ru.igar15.webapp.model;

public enum ContactType {
    PHONE("Phone"),
    MOBILE("Mobile"),
    HOME_PHONE("Home Phone"),
    EMAIL("mail");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
