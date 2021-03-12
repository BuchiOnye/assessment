### Secured Library RESTAPI Project

This project includes 
* implementation to retrieve items from a designated datasource
* algorithm to perform a periodic update of a server.


## Technologies
Project is created with:
* Java programming language
* Spring boot framework
* h2 relational database
* Hibernate
* Mockito library (for unit test)
* Docker


## Setup
To run this project, ensure you have previously installed Java Develoment Kit (JDK) (preferably version 1.8 or greater), Apache maven build tool and Microsoft SQL Server :

```
Navigate to root project directory 
$  cd ./assessment

Build application using
$ mvn clean package 

Create a desired directory external to the application folder
Navigate to target directory and copy the library-service.jar to desired directory 
$ cd ./target

$ cd assessment/src/main/resources

Copy configuration file (application.poperties) to same directory with previously copied jar file
Modify database connection credentials e.g datasource.url, datasource.username and datasource.password if desired
Run 
$ java -jar library-service.jar

Alternatively install docker on your device
Navigate to project directory

To build project,

Run 
docker build -t library-service:latest .

To start application
Run 
docker run --rm -it -p 8081:8081 --name library-service library-service:latest

```

## Launch
After a successful startup of the project, 
* access swagger api documentation link with [http://localhost:8081/swagger-ui.html#/library-controller](http://localhost:8081/swagger-ui.html#/library-controller)
* you can confirm startup status by accessing Health Check link: [http://localhost:8081/actuator/health](http://localhost:8081/actuator/health)
* Postman-collection [https://www.getpostman.com/collections/8442bc09427460d116c9](https://www.getpostman.com/collections/8442bc09427460d116c9)





