# Use an official Maven image as the base image
FROM maven:3.9.3-eclipse-temurin-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and download project dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the entire project to the container
COPY . .

# Build the project
RUN mvn clean install -DskipTests

# Use a lightweight JDK image for runtime
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose any ports your application requires (e.g., for a web UI)
EXPOSE 4000

# Run the application
CMD ["java", "-jar", "app.jar"]
