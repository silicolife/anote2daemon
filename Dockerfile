FROM maven:latest AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY --from=build /usr/src/app/target/anote2daemon/WEB-INF/lib /app/lib
COPY --from=build /usr/src/app/target/anote2daemon/WEB-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.silicolife.anote2daemon.Anote2DaemonSpringBoot"]