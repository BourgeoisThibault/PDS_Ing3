FROM openjdk:8-jdk-alpine

ADD serverrest.jar myapp.jar
RUN sh -c 'touch /myapp.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/myapp.jar"]