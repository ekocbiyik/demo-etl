FROM openjdk:21-jdk-slim

WORKDIR /app

COPY parser-engine/target/parser-engine-0.0.1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
