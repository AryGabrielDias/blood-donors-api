FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} blood-donors-api.jar
RUN bin/sh -c 'touch /blood-donors-api.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/blood-donors-api.jar"]