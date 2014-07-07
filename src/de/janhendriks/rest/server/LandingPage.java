package de.janhendriks.rest.server;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

@Path("/")
public class LandingPage {

    @Context
    private UriInfo uriInfo;
    @Context
    private SecurityContext securityContext;

    // Header to indicate UTF8 output
    final static String UTF8HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    // This method is called if HTML is requested
    @GET
    @Produces(MediaType.TEXT_HTML)
    public final String welcomePage() {
        //        final String authTest = securityContext.getAuthenticationScheme() + "|" + (securityContext.getUserPrincipal() != null ? securityContext.getUserPrincipal().getName() : "null") + "|" + securityContext.isUserInRole("jan");
        return UTF8HEADER + "<html><title>Willkommen</title>"
        + "<body><h1>Willkommen!</h1>" +
        " <h2><a href=\"palindrom\">Palindromcheck hier</a></h2></body></html> ";
    }

}
