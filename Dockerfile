FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/garagem.integrador.redhat-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

CMD ["--spring.config.location=file:/config/secret.properties"]

EXPOSE 8081