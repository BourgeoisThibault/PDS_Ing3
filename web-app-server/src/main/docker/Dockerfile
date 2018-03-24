FROM openjdk:8-jdk-alpine

ADD serverweb.war myapp.war
RUN sh -c 'touch /myapp.war'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/myapp.war"]