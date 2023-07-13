FROM openjdk:17
WORKDIR /app

COPY ./pom.xml /app
COPY ./.mvn ./.mvn
COPY ./mvnw .
COPY ./pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
COPY ./src ./src
RUN ./mvnw clean package -DskipTests

ARG PORT_MS=8081
ENV PORT $PORT_MS

EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "./target/project-entel-0.0.1-SNAPSHOT.jar"]