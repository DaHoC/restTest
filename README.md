# REST Test

Experimental program to play with REST (Representational State Transfer) functionality.
Includes RESTFUL server WebApp as well as simple java client.
The program uses [Jersey](http://jersey.java.net/) as library for REST functionality.

## Functionality

Checks if a passed in string via URL is a palindrome and outputs accordingly with the ability to provide different output formats such as plain text, XML, HTML.

## Usage

### Server

For example, import into Eclipse as Dynamic Web Project, and run on server. Tested with Apache Tomcat 6.0.35.

### Client

Run ClientTest in the project as java application and watch the output of the program.
The web browser can also be used as client, where the URL is [HOST]:[PORT]/de.janhendriks.rest.server/palindrom/[INPUTSTRING]