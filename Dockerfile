FROM openjdk:latest

ARG JAR_FILE=target/t-task-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","app.jar"]