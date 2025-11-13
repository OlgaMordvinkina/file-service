FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/tableWithMinNumer.xlsx /app/data/tableWithMinNumer.xlsx
ENV SERVER_PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
