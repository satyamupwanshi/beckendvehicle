# Use official OpenJDK image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy everything from project into the container
COPY . .

# Build the project inside the container
RUN ./mvnw package -DskipTests

# Run the application
CMD ["java", "-jar", "target/*.jar"]
