# Film rating API

## About
This project is a REST API in order to rate different films,
also it can offer links to services where you can watch films.
It allows users to perform CRUD operations on films, 
and handle authentication.
This project is built using Java with Spring Boot and Maven.

## Getting Started

* Postman collection is located in the root of the project

### Requirements
To run this project you'll need the following:
* **JDK**: Install Java Development Kit 19
* **Maven**: Install Apache Maven 4.0.0
* **Postgres**: Create database with your postgres user. Use command: `CREATE DATABASE film-rate WITH OWNER postgres`
* **application.yml**: Set up your credentials to Postgres DB with your username and password
* **mvn clean install**: Perform mvn clean install
* **Running project**: Run the Project
* **Initializing Roles**: Roles will automatically be added to the database after running the project

## API Endpoints

### Base URL
**http://localhost:8080**

### Auth controller
* POST `/auth/register`: Before calling authenticate endpoint
  you need to register by entering username and password. 
  Using this method of registration you can add only a user 
  with user role. After running the project to db will be added
  user with admin role. Username: `user1`, password: `pass123`
* POST `/auth/login`: Before calling other endpoints,
  you need to authenticate by
  using username and password that you used in register endpoint
* Then you need to put access token
  in all controller endpoints into authorization header: `Authorization: Bearer <access_token>`

### Film Controller
* GET `/film`: get all films
* GET `/film/{film id}`: get film by it's id
* POST `/film`: add a new film
* PUT `/film/{film id}`: update the film
* DEL `/film`: delete all films
* DEL `/film/{film id}`: delete film by it's id

### Rate Controller
* GET `/rate/{film id}`: get film's rates by film id
* GET `/rate/avg/{film id}`: get film's average rate
* POST `/rate/{film id}`: add rate to film by it's id

### Proposal Controller
* GET `/propose/{film id}`: get all links of the services to watch this film
* POST `/propose/{film id}`: add a link to the service where you can watch the film