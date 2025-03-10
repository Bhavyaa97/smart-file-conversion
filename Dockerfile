# Use an official OpenJDK runtime as a base image
FROM openjdk:21-jdk-slim

# Set a working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY target/smart-excel-json-parser-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that Spring Boot runs on
EXPOSE 8080

# Use a health check to ensure container is running
HEALTHCHECK --interval=30s --timeout=10s --retries=3 CMD curl -f http://localhost:8080/ || exit 1

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
