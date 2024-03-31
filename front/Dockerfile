FROM openjdk:17-jdk-slim AS build

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY pom.xml .

COPY src src
RUN mvn package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/front-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8090

CMD ["java", "-jar", "app.jar"]
