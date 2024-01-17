FROM oracle-jdk:8u152-alpine-3.7
COPY todo-app-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]