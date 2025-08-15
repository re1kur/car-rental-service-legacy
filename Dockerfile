FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

COPY . .

RUN mvn clean package -DskipTests

FROM tomcat:11-jre21-temurin

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build target/car-rental-service-legacy-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]