FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY --from=build /target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080