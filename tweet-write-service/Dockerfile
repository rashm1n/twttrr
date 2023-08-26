FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.rashm1n.twtr.tweetwriteservice.TweetWriteServiceApplication"]

#ENV TWEET_WRITE_ORACLE_DB_URL="jdbc:oracle:thin:@localhost:1521/freepdb1"
#ENV TWEET_WRITE_ORACLE_DB_USERNAME="tweetDb"
#ENV TWEET_WRITE_ORACLE_DB_PASSWORD="pwd"
#ENV TWEET_WRITE_KAFKA_BOOTSTRAP_URL="localhost:19092"
#ENV TWEET_WRITE_KAFKA_TOPIC="tweet"
#ENV TWEET_WRITE_SERVER_PORT=8090