FROM openjdk:21-jdk-slim

WORKDIR /app

COPY etl-manager/target/etl-manager-0.0.1.jar app.jar

COPY aggregation-engine/target/aggregation-engine-0.0.1.jar aggregation.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
