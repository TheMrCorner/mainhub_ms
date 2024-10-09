FROM openjdk:17-oracle

ADD /build/libs/mainhub-ms-0.0.3-SNAPSHOT.jar /opt/mainhub-ms-0.0.3-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/opt/mainhub-ms-0.0.3-SNAPSHOT.jar"]