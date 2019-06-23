FROM openjdk:8

ADD ./target/EmailValidation-0.0.1-SNAPSHOT.jar /usr/src/EmailValidation-0.0.1-SNAPSHOT.jar
EXPOSE 7999
WORKDIR usr/src
ENTRYPOINT ["java","-jar","EmailValidation-0.0.1-SNAPSHOT.jar"]
