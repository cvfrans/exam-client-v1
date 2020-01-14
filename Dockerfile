FROM openjdk:8-jre-alpine

ADD target/exam-client-0.0.1-SNAPSHOT.jar /clientapp.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/.urandom","-jar","/clientapp.jar"]
