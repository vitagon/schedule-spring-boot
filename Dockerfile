FROM openjdk:8-jre-alpine
EXPOSE 8080
ADD target/schedule-spring-boot.jar schedule-spring-boot.jar
ENV JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,address=0.0.0.0:5005,server=y,suspend=n"
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar schedule-spring-boot.jar