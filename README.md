### Spring Boot 3.3.3, Testcontainers, Postgres:latest

A REST API service and database backend for local web application development. The `Greeting API Service` project is a companion project for `Maven-Spring-Tomcat` web application project to provide local API services and database backing. However, `Greeting API Service` also stands on its own as a testable API service and is an example of single purpose applications, and local development using containerization. `Greeting API Service` project depends on the following technologies:

- `Docker Desktop` image and container provider
- `Boot-Spring-Data-Jpa` persistent data provider using Hibernate framework
- `Boot-Spring-Testcontainers` data and API service mocking (via Microcks) for automated testing
- `PostgreSQL` persistent data store for API service input/output

--

### About the Project

This project depends on `Spring framework` for the majority of the REST API functionality, and `Spring Boot` for configuration and deployment. It doesn't provide any user interface but does handle endpoint request and response and database CRUD functionality. There are two endpoints configured for this initial release. The service/endpoints serve as a general pattern for building out additional service/endpoints.

- [Create Greeting](https://github.com/rwhite35/sping-testcontainer-postgres/blob/master/src/main/java/com/demo/greetings/api/GreetingController.java)<br />

  - Handles `/api/greetings` POST request with a data object having one key/value for username:String input.

- [Read Greeted Name](https://github.com/rwhite35/sping-testcontainer-postgres/blob/master/src/main/java/com/demo/greetings/api/GreetingController.java)<br />
  - Handles `/api/greetings/{username}` GET request with URI parameter a user name to search for in the database.

Setup and development notes are included in the docs/ sub directory in PDF format for portability. The majority of the projects technical details are included in two documents, API Setup and Database Setup. Some screen grabs have also been included for context.

--

### PREREQUISITES

1. [Docker Desktop](https://www.docker.com/products/docker-desktop/)<br />

   - A free GUI application with command-line utilities for containerization and operation. Its not necessary to install Docker Desktop, however, this project assumed the application is installed and running. Some command-line has been included for convenience.

2. [Testcontainers](https://testcontainers.com)<br />

   - An Open Source framework for database, orchestration/federation, message brokering and similar cloud functions, but for localhost deployment. Testcontainers can integrate with AWS and similar cloud providers, but is ideal for quickly deploying testable service locally. This project depends on Spring Boot implementation of Testcontainers.

3. [Sping Boot](https://spring.io/projects/spring-boot)<br />

   - A String framework library for configuring and supporting a development environment be that local or cloud. Spring Boot is similar Terraform but specific to Spring Framework project. It also provides plumbing for much of the projects functionality.

4. [Postgres](https://www.postgresql.org)<br />

   - Is pulled from a docker image (postgres:latest) which targets the latest stable Postgres release (v16). Initially, the postgres:latest instance will have a default table called `test` with User `test` and Password `test`. That can all be configured but on the first runs instance, that will be the setup.

--

### BUILD/RUN DEBUG

The build and run - which will creates each required container - simple select the Testcontainers TestGreetingsApplication file with its main() method and run that in debug mode. Once the process has completed, there should be four containers with one being postgres:latest and another Microcks-Uber:1.8.1 for mocking data for automated testing. If that's given, follow the steps in Database Setup to test the database and connection, create a new record and read in a records username value.

Enjoy!
