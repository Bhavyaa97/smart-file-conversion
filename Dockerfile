# Use an OpenJDK image
FROM openjdk:21-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy the JAR from the current context
COPY smart-excel-json-parser-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
