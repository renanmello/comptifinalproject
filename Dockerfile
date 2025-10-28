# Stage 1: build
FROM maven:3.9.11-eclipse-temurin-21 AS build
WORKDIR /backend
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: runtime
FROM eclipse-temurin:21-jre
WORKDIR /backend

EXPOSE 8080

COPY --from=build /backend/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]

