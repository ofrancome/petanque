FROM registry.access.redhat.com/ubi8/openjdk-21:1.20 AS build
WORKDIR /app

USER jboss:jboss
COPY . ./

USER root
RUN chmod +x ./mvnw

USER jboss:jboss
RUN ./mvnw clean install

CMD ["java", "-jar",  "target/petanque-0.0.1-SNAPSHOT.jar"]