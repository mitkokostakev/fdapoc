# An example microservice which provides REST API for integration with FDA services

### Requirements :

1. OpenJDK-15
2. Maven 3
3. Postgres database instance

#### Running the microservice locally :
    mvn spring-boot:run 

    [optional] Run Spring Boot app with java -jar command :
    java -jar target/fda-demo-0.0.1-SNAPSHOT.jar

#### OpenAPI Documentation could be accessed at
    http://localhost:8001/swagger-ui.html