package de.janhendriks.rest.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.spi.resource.Singleton;

/**
 * Responsible for answering incoming palindrome check requests accordingly.
 * The class registers its methods for the HTTP GET request using the @GET annotation.
 * Using the @Produces annotation, it defines that it can deliver several MIME types, text, XML and HTML.
 * The browser requests per default the HTML MIME type.
 */
@Singleton
@Path("/palindrom")
public final class Palindrome {

    @Context
    private UriInfo uriInfo;

    // Header to indicate UTF8 output
    final static String UTF8HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    /**
     * Checks if input text is a palindrome.
     * @param inText input text
     * @return <code>true</code> if input text is a palindrome, <code>false</code> otherwise
     */
    private static final boolean isPalindrome(final String inText) {
        return inText.equalsIgnoreCase(new StringBuffer(inText).reverse().toString());
    }

    /**
     * Returns the absolute base URL including host and port
     * @return absolute base URL including host and port
     */
    private final String getAbsoluteBaseUrl() {
        return uriInfo.getBaseUri().getHost() + ":" + uriInfo.getBaseUri().getPort() + uriInfo.getBaseUri().getPath();
    }

    // This method is called if TEXT_PLAIN is requested
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{inText}")
    public final String checkForPalindromePlain(@PathParam("inText") final String inText) {
        return inText + " ist palindrom: " + Boolean.toString(isPalindrome(inText));
    }

    // This method is called if XML is requested
    @GET
    @Produces(MediaType.TEXT_XML)
    @Path("/{inText}")
    public final String checkForPalindromeXML(@PathParam("inText") final String inText) {
        return UTF8HEADER + "<palindrome value=" + Boolean.toString(isPalindrome(inText)) + ">" + inText + "</palindrome>";
    }

    // This method is called if HTML is requested
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/{inText}")
    public final String checkForPalindromeHTML(@PathParam("inText") final String inText) {
        return UTF8HEADER + "<html><title>Palindromcheck</title>"
                + "<body><h1>" + inText + " ist " + (isPalindrome(inText) ? "ein" : "kein") + " Palindrom!" + "</body></h1></html> ";
    }

    // This method is called if HTML without palindrome input string is requested
    @GET
    @Produces(MediaType.TEXT_HTML)
    public final String welcomePalindrome() {
        final String examplePalindrome = "testset";
        final String palindromeCheckPath = "palindrom/";
        return UTF8HEADER + "<html><title>Palindromcheck</title>"
        + "<body><h1>" + "Test auf Palindrom, zu prüfendes Wort an die URL hinter " + palindromeCheckPath + " anhängen, z.B. <a href=\"" + palindromeCheckPath + examplePalindrome + "\">" + getAbsoluteBaseUrl() + palindromeCheckPath + examplePalindrome + "</a></body></h1></html>";
    }

}
