FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/your-app.jar /app/app.jar
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5433/lib_db
ENV SPRING_DATASOURCE_USERNAME=
ENV SPRING_DATASOURCE_PASSWORD=
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
EXPOSE 8081
