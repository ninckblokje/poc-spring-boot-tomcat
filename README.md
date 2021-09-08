# poc-spring-boot-tomcat

A small Spring Boot project to test the integration with a standalone Tomcat.

Tomcat will provide the datasource and will manage the users / roles.

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
