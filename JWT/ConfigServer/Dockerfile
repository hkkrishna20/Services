FROM openjdk:8

ADD ./target/ConfigServer-0.0.1-SNAPSHOT.jar /usr/src/ConfigServer-0.0.1-SNAPSHOT.jar
EXPOSE 8761
WORKDIR usr/src
ENTRYPOINT ["java","-jar","ConfigServer-0.0.1-SNAPSHOT.jar"]
