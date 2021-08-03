FROM openjdk:11

WORKDIR /app

COPY .mvn/ .mvn
COPY petclinic-data ./petclinic-data
COPY petclinic-web ./petclinic-web

COPY mvnw pom.xml ./


RUN mvn dependency:go-offline

COPY src ./src


CMD ["./mvnw", "spring-boot:run"]