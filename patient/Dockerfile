## Use an official OpenJDK runtime as a base image
#FROM openjdk:17-jdk-slim AS build
#
## Install Maven
#RUN apt-get update && apt-get install -y maven
#
#LABEL application-name="patient-service"
#
## Set working directory
#WORKDIR /app
#
## Copy Maven configuration and dependencies
#COPY pom.xml .
#RUN mvn dependency:go-offline
#
## Copy source code and build the application
#COPY src src
#RUN mvn package -DskipTests
#
## Create a new stage for running the application
#FROM openjdk:17-jdk-slim
#
## Set working directory
#WORKDIR /app
#
## Copy the built JAR file
#COPY --from=build /app/target/patient-0.0.1-SNAPSHOT.jar app.jar
#
## Expose port 8080
#EXPOSE 8081
#
## Command to run the application
#CMD ["java", "-jar", "app.jar"]






# Use an official OpenJDK runtime as a base image for the build stage
#FROM openjdk:17-jdk-slim AS build
#
## Install Maven
#RUN apt-get update && apt-get install -y maven
#
## Set working directory
#WORKDIR /app
#
## Copy Maven configuration and dependencies
#COPY pom.xml .
#RUN mvn dependency:go-offline
#
## Copy source code
#COPY src src
#
## Build the application
#RUN mvn package -DskipTests
#
## Create a new stage for running the application
#FROM openjdk:17-jdk-slim
#
## Set working directory
#WORKDIR /usr/src/app
#
## Copy the built JAR file and application properties from the build stage
#COPY --from=build /app/target/patient-0.0.1-SNAPSHOT.jar patient.jar
#COPY --from=build /app/src/main/resources/application.properties .
#
## Replace localhost with host.docker.internal in application.properties
#RUN sed -i 's/localhost/host.docker.internal/g' application.properties
#
## Expose port 8081
#EXPOSE 8081
#
## Command to run the application
#CMD ["java", "-jar", "patient.jar"]

# Build Stage
FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /usr/src/app

# Copy Maven configuration and dependencies
COPY pom.xml .
#RUN mvn dependency:go-offline

# Copy source code
COPY src src

# Build the application
RUN mvn package -DskipTests

# Runtime Stage
FROM openjdk:17-jdk-slim AS runtime

WORKDIR /usr/src/app

# Copy the built JAR file from the build stage
COPY --from=build /usr/src/app/target/patient-0.0.1-SNAPSHOT.jar app.jar

# Copy application.properties
COPY src/main/resources/application.properties .

# Replace 'localhost' with 'host.docker.internal' in application.properties
RUN sed -i 's/localhost/host.docker.internal/g' application.properties

# Expose port 8081
EXPOSE 8081

# Command to run the application
CMD ["java", "-jar", "app.jar"]



