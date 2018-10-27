# CarRestApi 
Small app for Develop REST API endpoint with CRUD functionality on Cars data.
can list cars, delete car, update car, add new car.
## Installation

### Requirements
* Java 8
* Maven

### Build and Run
* Build with Maven.

Download the app. Extract the zip file. go inside project folder and run this maven commend.

`$ mvn spring-boot:run`

## Built With
* SpringBoot
* H2 simple in memory DB

## Using the endpoints
* Once the app is up and running. you can check API documentation from this url
[Swagger API Documentation](http://localhost:8080/swagger-ui.html)
* You can check CarController
* App using basic authentication with Spring Security username: user password: user123
* or add in request header Authorization as a key and dXNlcjo1MzU0YjA3Yy1lM2JmLTRlZmMtYjIyYy04OWQwZGFlY2ZkNzA= as value
* Ex: http://localhost:8080/api/v1/cars       GET     List All Cars 
* Ex: http://localhost:8080/api/v1/cars/{id}  GET     get car with id 
* Ex: http://localhost:8080/api/v1/cars       POST    save car
* Ex: http://localhost:8080/api/v1/cars/id    PUT     update car with id 
* Ex: http://localhost:8080/api/v1/cars/id    DELETE  delete car with id 

## Unit/Integeration Test
* The project having integeration test for all endpoint.

## DB using H2
* The project using H2 simple in memory DB.
* You can access the H2 console [HERE](http://localhost:8080/h2).
* username: sa password: sa

## Authentication
* The project using Spring Security for basic Authentication.
* username: user password: user123

## API Documentation
* [Swagger API Documentation](http://localhost:8080/swagger-ui.html)

## Actuator
* The project uses spring actuator for monitoring.
* [actuator metrics](http://localhost:8080/actuator/metrics)
* [actuator env](http://localhost:8080/actuator/env)
* [actuator health](http://localhost:8080/actuator/health)

## REST API style
* use Richardson Maturity Model for reference with Hateoas concept.


