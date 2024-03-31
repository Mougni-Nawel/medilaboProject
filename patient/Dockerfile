FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /usr/src/app

COPY pom.xml .
COPY src src

RUN mvn package -DskipTests

FROM openjdk:17-jdk-slim AS runtime

WORKDIR /usr/src/app

COPY --from=build /usr/src/app/target/patient-0.0.1-SNAPSHOT.jar app.jar

COPY src/main/resources/application.properties .

RUN sed -i 's/localhost/host.docker.internal/g' application.properties

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]
