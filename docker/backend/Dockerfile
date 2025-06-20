FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src 
RUN mvn clean package -DskipTests

FROM amazoncorretto:21
ARG PROFILE=prod
ENV ACTIVE_PROFILE=${PROFILE}
ARG APP_VERSION=1.0.0

WORKDIR /app
COPY --from=build /build/target/dev_network_community-*.jar /app/

EXPOSE 8080

# Utilisez un ARG pour passer MYSQL_URL lors du build, puis définissez-le comme variable d'environnement

ENV ACTIVE_PROFILE=${PROFILE}
ENV JAR_VERSION=${APP_VERSION}


CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} -Dspring.datasource.url=${MYSQL_URL} dev_network_community-${JAR_VERSION}.jar