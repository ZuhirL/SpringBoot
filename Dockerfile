FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
RUN apk --no-cache add curl
COPY --from=build /target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=30s --start-period=5s --retries=3 \
 CMD curl --fail http://localhost:8080/status/healthz || exit 1
