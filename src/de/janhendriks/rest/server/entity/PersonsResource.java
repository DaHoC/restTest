package de.janhendriks.rest.server.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

/**
 * Maps the resource to the person URIs
 */
@Path("/persons")
public class PersonsResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    /**
     * Return the list of persons to the user in the browser.
     * @return list of persons
     */
    @GET
    @Produces(MediaType.TEXT_XML)
    public List<Person> getPersonsBrowser() {
        List<Person> persons = new ArrayList<Person>();
        persons.addAll(PersonDao.INSTANCE.getModel().values());
        return persons;
    }

    /**
     * Return the list of persons for applications.
     * @return list of persons
     */
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<Person> getPersons() {
        List<Person> persons = new ArrayList<Person>();
        persons.addAll(PersonDao.INSTANCE.getModel().values());
        return persons;
    }

    /**
     * Use [URL]/persons/count to get the total number of records.
     * @return number of persons
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        int count = PersonDao.INSTANCE.getModel().size();
        return String.valueOf(count);
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newPerson(@FormParam("uid") String id, @FormParam("summary") String summary, @FormParam("description") String description,
            @Context HttpServletResponse servletResponse) throws IOException, NumberFormatException {
        /// Failsafe, TODO throw error message or let Long.valueOf throw NumberFormatException or automatically assign highest uid
        if (id == null) {
            id = "-1";
        }
        final Long uid = Long.valueOf(id);
        Person person = new Person(uid, summary);
        if (description != null) {
            person.setDescription(description);
        }
        PersonDao.INSTANCE.getModel().put(uid, person);

        servletResponse.sendRedirect("../persons/");
    }

    /**
     * Defines that the next path parameter after persons is treated as a parameter and passed to the PersonResources.
     * Allows to type [URL]/persons/1, where 1 will be treated as parameter and passed to PersonResource.
     * @param uid identifier of person to retrieve
     * @return PersonResource of given id
     */
    @Path("{person}")
    public PersonResource getPerson(@PathParam("person") String uid) {
        return new PersonResource(uriInfo, request, uid);
    }

}
