FROM maven:3.8.5-openjdk-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build ./app/target/*.jar ./eurekaServer.jar
EXPOSE 8761
ENTRYPOINT java -jar eurekaServer.jar