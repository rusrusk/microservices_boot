FROM openjdk:20
LABEL authors="rkultaev"


COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]