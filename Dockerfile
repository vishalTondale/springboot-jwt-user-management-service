# ============ BUILD STAGE =====================
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy only pom.xml first (for build caching)
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy all source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# ============ RUN STAGE =====================
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (Render uses its own env, but good practice)
EXPOSE 8080

# Tell Spring Boot to use Render's provided port
ENV SERVER_PORT=10000
ENV PORT=10000

# Start the application
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]

