FROM openjdk:8-jre-alpine
VOLUME /tmp
EXPOSE 8080
ADD target/schedule-spring-boot.jar schedule-spring-boot.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-Djava.security.egd=file:/dev/./urandom", "-jar", "schedule-spring-boot.jar"]