FROM openjdk:17-jdk-alpine
COPY target/domaci-0.0.1-SNAPSHOT
ENTRYPOINT ["java","-jar","/domaci-0.0.1-SNAPSHOT.jar"]