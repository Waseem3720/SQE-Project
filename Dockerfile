# Use an OpenJDK base image
FROM openjdk:17
WORKDIR /app
# Copy project files into the image
COPY . .
# Compile Java source files
RUN javac -d out src/*.java
# Run the Java app
CMD ["java", "-cp", "out", "LibraryManagementSystemMain"]
