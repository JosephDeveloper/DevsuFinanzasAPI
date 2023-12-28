FROM openjdk:17-alpine as builder

WORKDIR /devsu-finanzas

COPY ./.mvn ./.mvn
COPY ./mvnw .
COPY ./pom.xml .
COPY ./src ./src
COPY ./config ./config

RUN ./mvnw clean package -Pserver -DskipTests

FROM openjdk:17-alpine
WORKDIR /devsu-finanzas
COPY --from=builder /devsu-finanzas/target/devsu-finanzas.jar .
COPY --from=builder /devsu-finanzas/config/ ./config

EXPOSE 8001
ENTRYPOINT [ "java", "-jar", "devsu-finanzas.jar"]