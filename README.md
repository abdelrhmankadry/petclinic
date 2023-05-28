# Pet Clinic REST API

This is a Java Spring Boot project that provides a REST API for a Pet Clinic application.

## Features

- Manage owners, pets, and visits
- CRUD operations for pet clinic entities
- RESTful API endpoints

## Requirements

- Java 11 or higher
- Maven
- Docker (optional)

## Getting Started

1. Clone the repository:

```
git clone https://github.com/abdelrhmankadry/petclinic.git
```

2. Navigate to the project directory:

```
cd pet-clinic-rest-api
```

3. Build the project:

```
mvn clean install
```

4. Run the application:

```
mvn spring-boot:run
```

The REST API will be available at `http://localhost:8080`.



## Docker

A Dockerfile is provided to build a Docker image of the application. To build the image, run:

```
docker build -t petclinic-rest-api .
```

To run the application in a Docker container, execute:

```
docker run -p 8080:8080 petclinic-rest-api
```

## CircleCI

This project includes a CircleCI configuration for continuous integration. The configuration is defined in the `.circleci/config.yml` 


This workflow is triggered on every push and runs the Maven test job.
