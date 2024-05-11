FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /dataService
COPY . /dataService/.
RUN mvn -f /dataService/pom.xml clean package -Dmaven.test.skip=true

FROM eclipse-temurin:17-jre-alpine
WORKDIR /dataService
COPY --from=builder /dataService/target/*.jar /dataService/*.jar
ENTRYPOINT ["java", "-jar", "/dataService/*.jar"]