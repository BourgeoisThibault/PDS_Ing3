FROM openjdk:8-jdk-alpine

ADD dataaccess.jar myapp.jar
RUN sh -c 'touch /myapp.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/myapp.jar", "--spring.datasource.url=jdbc:mysql://192.154.88.161:3306/esibank_proto_db?useSSL=false"]
