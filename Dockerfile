FROM gradle:7.6.1-jdk17 AS build

WORKDIR /app

COPY build.gradle settings.gradle ./
COPY src ./src

RUN gradle build --no-daemon

FROM openjdk:17

WORKDIR /app

ENV APP_NAME=lanchonete
ENV JAR_FILE=${APP_NAME}-0.0.1-SNAPSHOT.jar

COPY --from=build /app/build/libs/${JAR_FILE} /app/${APP_NAME}-app.jar

ENTRYPOINT ["java", "-jar", "lanchonete-app.jar"]

EXPOSE 8080
