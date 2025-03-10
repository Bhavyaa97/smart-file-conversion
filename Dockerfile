# Use official OpenJDK image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file from the target folder
COPY /target/smart-excel-json-parser-0.0.1-SNAPSHOT.jar app.jar

# Expose the port (Render uses PORT environment variable)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
