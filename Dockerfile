FROM openjdk:8-jre-alpine
VOLUME /tmp
EXPOSE 8080
EXPOSE 8000
ADD target/schedule-spring-boot.jar schedule-spring-boot.jar
ENTRYPOINT ["java", "-Xdebug", "-Xrunjdwp:server=y,transport=dt_socket,suspend=n", "-jar", "schedule-spring-boot.jar"]