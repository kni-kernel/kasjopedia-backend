FROM openjdk:14 as builder
ENV WORKDIR="/kasj"

WORKDIR ${WORKDIR}
COPY build.gradle gradlew gradle.properties settings.gradle resources/application.conf ${WORKDIR}/
COPY gradle ${WORKDIR}/gradle
RUN ./gradlew build --no-daemon

FROM openjdk:14
ENV WORKDIR="/kasj"
WORKDIR ${WORKDIR}

EXPOSE 8080
COPY --from=builder ${WORKDIR}/build/libs/kasjopedia-backend.jar  ${WORKDIR}/
COPY resources/application.conf ${WORKDIR}/

COPY build/libs/kasjopedia-backend.jar ${WORKDIR}/
CMD ["java", "-server", "-jar", "kasjopedia-backend.jar"]
