FROM openjdk:8u181-jre-alpine

MAINTAINER Daniel Lavoie <dlavoie@live.ca>

ADD spring-boot-sample-blockchain.jar /app/spring-boot-sample-blockchain.jar

EXPOSE 8080

CMD ["java", "-Xmx512m", "-jar", "/app/spring-boot-sample-blockchain.jar"]