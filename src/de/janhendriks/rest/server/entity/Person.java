package de.janhendriks.rest.server.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {
    private Long uid;
    private String summary;
    private String description;

    public Person() {
    }

    public Person(Long uid, String summary) {
        this.uid = uid;
        this.summary = summary;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}