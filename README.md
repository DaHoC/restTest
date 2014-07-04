# REST Test

Experimental program to play with REST (Representational State Transfer) functionality.
Includes RESTFUL server WebApp as well as simple java client.
The program uses [Jersey](http://jersey.java.net/) as library for REST functionality.

## Functionality

Checks if a passed in string via URL is a palindrome and outputs accordingly with the ability to provide different output formats such as plain text, XML, HTML.

## Setup 

* Retrieve the source code via zip or git clone
* Import into e.g. Eclipse as existing project or new Dynamic Web Project and set Dynamic Web Module 2.5 and Java 1.6 (Project > Properties > Project Facets) 

## Usage

### Server

Deploy and run on server. Tested with Apache Tomcat 6.0.35.

### Client

Run ClientTest in the project as java application (Right-click on de.janhendriks.rest.client.ClientTest.java > Run As > Java Application) and watch the output of the program.

The web browser can also be used as client, where the URL is 
	[HOST]:[PORT]/[BASEURL]/palindrom/[INPUTSTRING]

Possible command-line arguments: -host [address] -port [port] -baseurl [baseurl]

Default command-line arguments: -host 127.0.0.1 -port 8180 -baseurl RestTest
