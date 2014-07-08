package de.janhendriks.rest.server.entity;

import java.util.HashMap;
import java.util.Map;

public enum PersonDao {
    INSTANCE;

    private Map<Long, Person> contentProvider = new HashMap<Long, Person>();

    private PersonDao() {
        Person person = new Person(1L, "Learn REST");
        person.setDescription("Read http://www.vogella.com/tutorials/REST/article.html");
        contentProvider.put(1L, person);
        person = new Person(2L, "Do something");
        person.setDescription("Read complete http://www.vogella.com");
        contentProvider.put(2L, person);
    }

    public Map<Long, Person> getModel() {
        return contentProvider;
    }

}