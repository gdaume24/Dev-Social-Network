FROM maven:3.8.7-openjdk-23 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src 
RUN mvn clean package -DskipTests

FROM amazoncorretto:23

WORKDIR /app
COPY --from=build /