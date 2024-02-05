FROM openjdk:17-oracle

ADD /build/libs/journal-0.0.1-SNAPSHOT.jar /opt/journal-0.0.1-SNAPSHOT.jar

EXPOSE 5687

ENTRYPOINT ["java", "-jar", "/opt/journal-0.0.1-SNAPSHOT.jar"]