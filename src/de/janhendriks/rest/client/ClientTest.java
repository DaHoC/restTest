package de.janhendriks.rest.client;

import java.net.URI;
import java.util.Arrays;
import java.util.Iterator;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Client for interfacing the RESTful palindrome web service.
 */
public class ClientTest {

    private static String SERVER_ADDRESS = "127.0.0.1";
    private static int SERVER_PORT = 8180;
    private static String SERVER_BASE_URL = "RestTest";

    private static StringBuilder values = new StringBuilder("testset");

    /**
     * Main client program entry point.
     * @param args -host [address] -port [port] -baseurl [baseurl] [values]
     */
    public static void main(String[] args) {
        System.out.println("Starting client...");
        /// Parse and set command-line arguments
        try {
            parseCommandLineArguments(args);
        } catch (IllegalArgumentException iae) {
            System.err.println("Error parsing port number: " + iae.getLocalizedMessage());
            System.err.println("Fallback to " + Integer.toString(SERVER_PORT));
        }
        System.out.println("Using URI " + SERVER_ADDRESS + ":" + Integer.toString(SERVER_PORT) + "/" + SERVER_BASE_URL);
        final ClientConfig config = new DefaultClientConfig();
        final Client client = Client.create(config);
        try {
            final WebResource service = client.resource(getBaseURI());
            System.out.println(service.path("palindrom").path(values.toString()).accept(MediaType.TEXT_PLAIN).get(String.class));
            System.out.println(service.path("palindrom").path(values.toString()).accept(MediaType.TEXT_XML).get(String.class));
            System.out.println(service.path("palindrom").path(values.toString()).accept(MediaType.TEXT_HTML).get(String.class));
        } catch (com.sun.jersey.api.client.ClientHandlerException che) {
            System.err.println("Error: Server at port not accessible: " + che.getLocalizedMessage());
        } catch (com.sun.jersey.api.client.UniformInterfaceException uie) {
            System.err.println("Error: Baseurl probably not correct: " + uie.getLocalizedMessage());
        }
    }

    /**
     * Retrieves the complete URI for the RESTful palindrome web service.
     * @return URI of RESTful palindrome service
     * @throws IllegalArgumentException
     */
    private static URI getBaseURI() throws IllegalArgumentException {
        return UriBuilder.fromUri("http://" + SERVER_ADDRESS + ":" + Integer.toString(SERVER_PORT) + "/" + SERVER_BASE_URL).build();
    }

    /**
     * Parses command-line arguments and sets respective variable values.
     * @param args command-line arguments
     * @throws IllegalArgumentException
     */
    private static void parseCommandLineArguments(final String[] args) throws IllegalArgumentException {
        if (args.length == 0) {
            /// Give a little help
            System.out.println("Possible command-line arguments: -host [address] -port [port] -baseurl [baseurl]");
            // System.out.println("Default command-line arguments: -host " + SERVER_ADDRESS + " -port " + Integer.toString(SERVER_PORT) + " -baseurl " + SERVER_BASE_URL);
            return;
        }
        // Walk through arguments, set up server address and port:
        final Iterator<String> argsIter = Arrays.asList(args).iterator();
        while(argsIter.hasNext()) {
            String val = argsIter.next();
            if ("-host".equals(val)) {
                // then the following should be the address (-host localhost)
                SERVER_ADDRESS = argsIter.next();
                System.out.println("Set host to: " + SERVER_ADDRESS);
            } else if ("-port".equals(val)) {
                // then the following should be the port (-port 8080)
                final String portNum = argsIter.next();
                try {
                    SERVER_PORT = Integer.parseInt(portNum);
                    System.out.println("Set port to: "+ Integer.toString(SERVER_PORT));
                }
                catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException(nfe);
                }
            } else if ("-baseurl".equals(val)) {
                SERVER_BASE_URL = argsIter.next();
                System.out.println("Set baseurl to: " + SERVER_BASE_URL);
            } else {
                // it's a value
                if (values.length() > 0) {
                    values.append(" ");
                }
                values.append(val);
            }
        }
    }
}
