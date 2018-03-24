FROM openjdk:8-jdk-alpine

ADD payfreeclt.jar myapp.jar
RUN sh -c 'touch /myapp.jar'

EXPOSE 8080
EXPOSE 1234

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/myapp.jar"]