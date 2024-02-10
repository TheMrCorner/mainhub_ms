FROM openjdk:17-oracle

ADD /build/libs/journal-0.0.2-SNAPSHOT.jar /opt/journal-0.0.2-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/opt/journal-0.0.2-SNAPSHOT.jar"]