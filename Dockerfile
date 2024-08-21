FROM registry.access.redhat.com/ubi8/openjdk-17:1.11 AS build
# Set a default directory inside the container to work from.
WORKDIR /app

# Copy the special Maven files that help us download dependencies.
COPY . .
RUN ls -al
# Download all the required project dependencies.
RUN ./mvnw dependency:resolve

# Copy our actual project files (code, resources, etc.) into the container.
COPY src. /src

# When the container starts, run the Spring Boot app using Maven.
CMD ["./mvnw", "spring-boot:run"]