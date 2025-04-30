FROM eclipse-temurin:21-jdk-alpine

# Quitar la línea de VOLUME ya que no está permitida en Railway
# VOLUME /tmp

COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080
