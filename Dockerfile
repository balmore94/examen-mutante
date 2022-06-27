FROM openjdk:8-jdk-alpine
EXPOSE 80
ARG JAR_FILE=target/examen-fichosa-mutante-0.0.1.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]