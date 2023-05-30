FROM openjdk:latest
ENV DB_HOST= \
    DB_NAME= \
    DB_PASSWORD= \
    DB_USERNAME= \
    DB_PORT=
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
