FROM registry.access.redhat.com/ubi8/openjdk-21:1.20 AS build
USER jboss:jboss
WORKDIR /opt/workspace

COPY .mvn .mvn/
COPY src src/
COPY Dockerfile ./Dockerfile
COPY mvnw ./mvnw
COPY pom.xml ./pom.xml

USER root
RUN chmod +x ./mvnw
RUN chown -R jboss /opt/workspace
USER jboss:jboss
RUN ./mvnw clean package

CMD ["java", "-jar",  "target/petanque-1.0.0.jar"]