FROM maven:latest AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

FROM openjdk:8-jdk-alpine

MAINTAINER Ruben Rodrigues <rrodrigues@ceb.uminho.pt>

RUN addgroup -S spring && adduser -S spring -G spring && mkdir /var/log/anote2daemon && chown spring:spring /var/log/anote2daemon
USER spring:spring

ENV H2DBPATH=/app/databases/h2
ENV H2DBSCHEMA=anote2daemon
ENV H2DBUSER=admin
ENV H2DBPASS=admin
ENV LUCENEPATH=/app/databases/lucene

COPY --from=build /usr/src/app/target/anote2daemon/WEB-INF/lib /app/lib
COPY --from=build /usr/src/app/target/anote2daemon/WEB-INF/classes /app

#Creating integrated db
RUN mkdir -p ${H2DBPATH} && mkdir -p ${LUCENEPATH}
RUN java -cp "app:app/lib/*" "com.silicolife.anote2daemon.utils.GenerateIntegratedDB" ${H2DBPATH} ${H2DBSCHEMA} ${H2DBUSER} ${H2DBPASS} 

EXPOSE 8080

ENTRYPOINT ["java","-cp","app:app/lib/*","com.silicolife.anote2daemon.Anote2DaemonSpringBoot"]