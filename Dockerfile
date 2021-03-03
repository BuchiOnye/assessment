FROM maven:3.6.3-jdk-8 AS MAVEN_BUILD

WORKDIR /build/

COPY pom.xml /build/

COPY src /build/src/

RUN mvn clean -U package

FROM openjdk:8u212-alpine3.9

WORKDIR /app

EXPOSE 8080

COPY --from=MAVEN_BUILD /build/target/library-service.jar /app

ENTRYPOINT ["java", "-jar", "library-service.jar"]