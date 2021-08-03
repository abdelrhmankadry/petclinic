FROM openjdk:11

WORKDIR /app

RUN apt-get update
RUN apt-get install -y maven

COPY .mvn/ .mvn
COPY petclinic-data ./petclinic-data
COPY petclinic-web ./petclinic-web

COPY mvnw pom.xml ./


RUN mvn dependency:go-offline

RUN mvn test

COPY src ./src


CMD ["mvn", "spring-boot:run"]