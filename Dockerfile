FROM openjdk

WORKDIR /app

COPY target/store-system-0.0.1-SNAPSHOT.jar /app/store.jar

ENTRYPOINT ["java", "-jar", "/app/store.jar"]