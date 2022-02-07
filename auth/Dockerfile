FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} auth.jar
ENTRYPOINT ["java","-jar","/auth.jar"]