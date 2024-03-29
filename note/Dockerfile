# Use an official OpenJDK runtime as a base image
FROM openjdk:17-jdk-slim AS build

# Install Maven
RUN apt-get update && apt-get install -y maven

# Set working directory
WORKDIR /app

# Copy Maven configuration and dependencies
COPY pom.xml .
#RUN mvn dependency:go-offline

# Copy source code and build the application
COPY src src
RUN mvn package -DskipTests

# Install MongoDB client
RUN apt-get update && \
    apt-get install -y wget gnupg && \
    wget -qO - https://www.mongodb.org/static/pgp/server-5.0.asc | apt-key add - && \
    echo "deb http://repo.mongodb.org/apt/debian buster/mongodb-org/5.0 main" | tee /etc/apt/sources.list.d/mongodb-org-5.0.list && \
    apt-get update && \
    apt-get install -y mongodb-org-tools

# Create a new stage for running the application
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built JAR file
COPY --from=build /app/target/note-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8082

# Command to run the application
CMD ["java", "-jar", "app.jar"]
