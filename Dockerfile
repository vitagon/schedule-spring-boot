FROM openjdk:8-jre-alpine
VOLUME /tmp
EXPOSE 8080
ADD target/schedule-spring-boot.jar schedule-spring-boot.jar
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,address=0.0.0.0:5005,server=y,suspend=n", "-jar", "schedule-spring-boot.jar"]