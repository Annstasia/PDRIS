FROM eclipse-temurin:21-jre-jammy
COPY ./target/Cat.jar /app/runner.jar
EXPOSE 8087
ENTRYPOINT ["java","-jar","/app/runner.jar"]
