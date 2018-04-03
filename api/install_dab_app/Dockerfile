FROM openjdk:8-jdk-alpine

ADD payfreeclt.jar myapp.jar
RUN sh -c 'touch /myapp.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/myapp.jar","--url.secure=https://ws.esibank.inside.esiag.info/"]