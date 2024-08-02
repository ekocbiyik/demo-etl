FROM openjdk:21-jdk-slim

WORKDIR /app

COPY download-engine/target/download-engine-0.0.1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
