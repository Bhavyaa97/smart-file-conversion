# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and pom.xml to build the app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Give execution permission to Maven wrapper
RUN chmod +x ./mvnw

# Debug: Check if files are copied
RUN ls -la

# Build the application
RUN ./mvnw clean package -DskipTests || (echo "Maven build failed!" && exit 1)

# Debug: Check if target folder exists and what's inside
RUN ls -la target/ || (echo "Target folder not found!" && exit 1)

# Copy the built JAR file into the container
COPY target/smart-excel-json-parser-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that Spring Boot runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
