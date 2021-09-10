# poc-spring-boot-tomcat

A small Spring Boot project to test the integration with a standalone Tomcat.

Tomcat will provide the datasource and will manage the users / roles. Additional configuration will
be read from the Tomcat `lib` folder.

## Tomcat Setup

Define a role `BOOT` and a user `boot` in `tomcat-users.xml`:

````xml
  <role rolename="BOOT"/>
  <user username="boot" password="Dummy_123" roles="BOOT" />
````

Define a HSQLDB datasource in `context.xml` (and add the driver to the `lib` folder):

````xml
  <Resource id="HSQLDB" type="javax.sql.DataSource">
    JdbcDriver  = org.hsqldb.jdbcDriver
    JdbcUrl     = jdbc:hsqldb:file:data/hsqldb/hsqldb
    UserName    = sa
    Password    = 
  </Resource>
````

Create a folder in the `lib` directory called `poc-spring-boot-tomcat` and place an `application.properties`
file there:

````properties
app.hello.message=Hello %s from TomEE!
````
