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

# Create a new stage for running the application
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built JAR file
COPY --from=build /app/target/front-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8090

# Command to run the application
CMD ["java", "-jar", "app.jar"]
