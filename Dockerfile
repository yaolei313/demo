FROM maven:3.9.5-eclipse-temurin-17 as builder

ARG BUILD_DIR=/build

WORKDIR ${BUILD_DIR}

COPY settings.xml pom.xml ./
COPY src ./src
RUN mvn -s settings.xml -B package -Dmaven.test.skip=true && \
    cp target/*.jar application.jar && \
    java -Djarmode=layertools -jar application.jar extract

FROM eclipse-temurin:17-jdk-jammy

ARG WORK_DIR=/app
WORKDIR ${WORK_DIR}

COPY --from=builder /build/dependencies/ ./
COPY --from=builder /build/spring-boot-loader/ ./
COPY --from=builder /build/snapshot-dependencies/ ./
COPY --from=builder /build/application/ ./

CMD ["java", \
  "org.springframework.boot.loader.launch.JarLauncher", \
  "--spring.profiles.active=local"]