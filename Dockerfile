# Usa imagen base con Maven
FROM maven:3.9-eclipse-temurin-21-alpine AS build

# Copia el proyecto completo
COPY . /app
WORKDIR /app

# Construye el jar dentro del contenedor
RUN mvn clean package -DskipTests

# Nueva etapa con JDK liviano
FROM eclipse-temurin:21-jdk-alpine
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080
