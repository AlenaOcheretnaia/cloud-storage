#
# Build stage
#
FROM maven:3.8.2-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/aloch-0.0.1-SNAPSHOT.jar /usr/local/lib/cloud-storage.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/usr/local/lib/cloud-storage.jar"]