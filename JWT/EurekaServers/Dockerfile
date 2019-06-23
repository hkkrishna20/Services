FROM openjdk:8

ADD ./target/EurekaServers-0.0.1-SNAPSHOT.jar /usr/src/EurekaServers-0.0.1-SNAPSHOT.jar
EXPOSE 8761
WORKDIR usr/src
ENTRYPOINT ["java","-jar","EurekaServers-0.0.1-SNAPSHOT.jar"]
