FROM openjdk:8-jdk-alpine
COPY build/libs/svc-web-content-picker-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/app.jar"]