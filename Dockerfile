FROM openjdk:14 as builder
ENV WORKDIR="/kasj"

WORKDIR ${WORKDIR}
COPY .  ${WORKDIR}/
RUN ./gradlew build --no-daemon -x test

FROM openjdk:14
ENV WORKDIR="/kasj"
WORKDIR ${WORKDIR}

EXPOSE 8080
COPY --from=builder ${WORKDIR}/build/libs/kasjopedia-backend.jar  ${WORKDIR}/
COPY resources/application.conf ${WORKDIR}/

CMD ["java", "-server", "-jar", "kasjopedia-backend.jar"]
