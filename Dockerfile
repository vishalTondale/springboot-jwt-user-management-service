# ============ BUILD STAGE =====================
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

# ============ RUN STAGE =====================
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# Render dynamically sets PORT env var
EXPOSE 8080

# Start application using Render’s assigned port
ENTRYPOINT ["sh", "-c", "java -Dserver.port=$PORT -jar app.jar"]
