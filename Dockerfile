FROM eclipse-temurin:25-jdk
ARG JAR_FILE=target/image-serve-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} image-serve-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "image-serve-service.jar"]