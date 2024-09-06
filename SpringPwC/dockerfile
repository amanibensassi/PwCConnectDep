FROM maven:3.9.7-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build app/target/EspritGather-0.0.1-SNAPSHOT.jar EspritGather-0.0.1.jar
ENTRYPOINT ["java","-jar","/app/EspritGather-0.0.1.jar"]
