FROM openjdk:17-jdk-bullseye as builder
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM openjdk:17-jdk-bullseye
COPY --from=builder dependencies/ ./app
COPY --from=builder snapshot-dependencies/ ./app
COPY --from=builder spring-boot-loader/ ./app
COPY --from=builder application/ ./app

WORKDIR /app

ENTRYPOINT java \
    -XX:+HeapDumpOnOutOfMemoryError \
    -XX:+ExitOnOutOfMemoryError \
    -XX:HeapDumpPath="/bp-backend.hprof" \
    -Xmx256m \
    -Dspring.profiles.active=$EXEC_ENV \
    org.springframework.boot.loader.launch.JarLauncher