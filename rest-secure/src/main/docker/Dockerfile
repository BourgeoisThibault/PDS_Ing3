FROM openjdk:8-jdk-alpine

ADD securerest.jar myapp.jar
RUN sh -c 'touch /myapp.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/myapp.jar", "--server.ssl.key-alias=esibank", "--url.database=http://esibanklab_dataaccess:8080/payfree/"]