FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src 
RUN mvn clean package -DskipTests

FROM amazoncorretto:21
ARG PROFILE=dev
ARG APP_VERSION=1.0.1

WORKDIR /app
COPY --from=build /build/target/dev_network_community-*.jar /app/

EXPOSE 8088

ENV MYSQL_URL=jdbc:mysql://dsn_db:3306/network?allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8
ENV MYSQL_USERNAME=user
ENV MYSQL_PASSWORD=123456
ENV SECURITY_JWT_SECRET_KEY=mY5nR9pXvLw3qAeZtGcHjKbVfDx7zC1rT6yU8iWlO0oP4sQ2dE9aB3hJkM
ENV SECURITY_JWT_EXPIRATION_TIME=864000000
ENV ACTIVE_PROFILE=${PROFILE}
ENV JAR_VERSION=${APP_VERSION}


CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} -Dspring.datasource.url=${MYSQL_URL} dev_network_community-${JAR_VERSION}.jar