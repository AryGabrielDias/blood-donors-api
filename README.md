# blood-donors-api

For building and running the application you need:

- [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven 3](https://maven.apache.org)
- [Docker](https://docs.docker.com/get-docker/)

### Create a docker local instance to run the containers locally
- Running docker-compose.yml in project root
- Open a terminal in the project root folder and run the commands below

```shell
mvn clean install -DskipTests
```

```shell
docker-compose up
```

- After docker builds the necessary containers, the application will start at the default URL
```shell
http://localhost:8080
```

### API Swagger (Endpoints and Payloads Documentation)
```shell
http://localhost:8080/swagger-ui/index.html#/
```