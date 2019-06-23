FROM openjdk:8

ADD ./target/UserProfile-0.0.1-SNAPSHOT.jar /usr/src/UserProfile-0.0.1-SNAPSHOT.jar
EXPOSE 7000
WORKDIR usr/src
ENTRYPOINT ["java","-jar","UserProfile-0.0.1-SNAPSHOT.jar"]
