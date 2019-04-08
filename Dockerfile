FROM openjdk:8-jre-alpine
VOLUME /tmp
EXPOSE 8080
ADD target/schedule-spring-boot.jar schedule-spring-boot.jar
ENTRYPOINT ["java", "-jar", "schedule-spring-boot.jar"]