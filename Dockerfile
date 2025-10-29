FROM  jelastic/maven:3.9.5-openjdk-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-slim
WORKDIR /app
COPY --from=build /app/target/*.jar exerlytix-backend1.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "exerlytix-backend1.jar"]

