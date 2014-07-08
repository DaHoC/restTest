package de.janhendriks.rest.server.entity;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

public class PersonResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String uid;

    public PersonResource(UriInfo uriInfo, Request request, String uid) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.uid = uid;
    }

    // Application integration
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Person getPerson() {
        Person person = PersonDao.INSTANCE.getModel().get(uid);
        if (person == null) {
            throw new RuntimeException("Get: Person with " + uid + " not found");
        }
        return person;
    }

    // for the browser
    @GET
    @Produces(MediaType.TEXT_XML)
    public Person getPersonHTML() {
        Person person = PersonDao.INSTANCE.getModel().get(uid);
        if (person == null) {
            throw new RuntimeException("Get: Person with " + uid + " not found");
        }
        return person;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putPerson(JAXBElement<Person> person) {
        Person c = person.getValue();
        return putAndGetResponse(c);
    }

    @DELETE
    public void deletePerson() {
        Person person = PersonDao.INSTANCE.getModel().remove(uid);
        if (person == null) {
            throw new RuntimeException("Delete: Person with " + uid + " not found");
        }
    }

    private Response putAndGetResponse(Person person) {
        Response res;
        if (PersonDao.INSTANCE.getModel().containsKey(person.getUid())) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        PersonDao.INSTANCE.getModel().put(person.getUid(), person);
        return res;
    }

}