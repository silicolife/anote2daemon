FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG DEPENDENCY=target/anote2daemon
COPY ${DEPENDENCY}/WEB-INF/lib /app/lib
COPY ${DEPENDENCY}/WEB-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.silicolife.anote2daemon.Anote2DaemonSpringBoot"]