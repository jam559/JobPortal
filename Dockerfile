FROM openjdk:17
COPY target/jobportal.jar jobportal.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "jobportal.jar"]