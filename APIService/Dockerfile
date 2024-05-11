FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /apiService
COPY . /apiService/.
RUN mvn -f /apiService/pom.xml clean package -Dmaven.test.skip=true

FROM eclipse-temurin:17-jre-alpine
WORKDIR /apiService
COPY --from=builder /apiService/target/*.jar /apiService/*.jar
ENTRYPOINT ["java", "-jar", "/apiService/*.jar"]