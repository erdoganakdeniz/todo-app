
# Basic TO-DO app

It is like
Wunderlist and Todoist.In the application, users should be able to register and create their own to-do
lists.



## Technology Stack

- Java 8
- Spring Boot
- Spring Data Couchbase
- Couchbase
- Swagger
- Maven
- Docker
- JUnit and Mockito


## Installation
Prerequisites:
*  Docker
*  Docker Compose

```
docker-compose up -d
```

## Build/Package
```
mvn clean package
```

## Testing
```
mvn test
```
## Manuel Setup

- Setting Couchbase

```
docker run -d --name db3 -p 8091-8096:8091-8096 -p 11210-11211:11210-11211 couchbase
```

After that, you can access couchbase interface via *http://localhost:8091*

When you open couchbase interface, you have to create a cluster that has username *"Administrator"* password *"123456"*

And then create a bucket named *"todo-app"*

And create a primary index on this bucket with following query.

```
CREATE PRIMARY INDEX ON `todo-app`._default._default;
```

- Running app

```
mvn clean install
mvn spring-boot:run
```
