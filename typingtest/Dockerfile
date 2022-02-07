FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} typingtest.jar
ENTRYPOINT ["java","-jar","/typingtest.jar"]