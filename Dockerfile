# Etapa de build
FROM gradle:7.6.1-jdk17 AS build

WORKDIR /app

COPY build.gradle settings.gradle ./
COPY src ./src

RUN gradle build --no-daemon

# Etapa de runtime
FROM openjdk:17-slim

WORKDIR /app

ENV APP_NAME=lanchonete
ENV JAR_FILE=${APP_NAME}-0.0.1-SNAPSHOT.jar

COPY --from=build /app/build/libs/${JAR_FILE} /app/${APP_NAME}-app.jar

# Baixar e configurar o wait-for-it.sh
RUN apt-get update && apt-get install -y curl \
    && curl -o /app/wait-for-it.sh https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh \
    && chmod +x /app/wait-for-it.sh

ENTRYPOINT ["/app/wait-for-it.sh", "svc-mysql-db:3306", "--", "java", "-jar", "/app/lanchonete-app.jar"]

EXPOSE 8080
