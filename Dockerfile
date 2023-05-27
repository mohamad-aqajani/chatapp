FROM openjdk:latest
ENV DB_HOST=chatapp.ccq1brmfpy92.eu-central-1.rds.amazonaws.com \
    DB_NAME=chatapp \
    DB_PASSWORD=iran1580011 \
    DB_USERNAME=mohammad \
    DB_PORT=5432
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]