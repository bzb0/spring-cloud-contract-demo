# Spring Cloud Contract Demo

### Description

This a demo Spring Boot application that demonstrates the implementation of consumer-driven contract tests with the `Spring Cloud Contract` framework.
The project contains two modules, namely an API provider (cloud-contract-provider) and an API consumer (cloud-contract-consumer). There are three
contracts, as one contract defines a single request/response pair. The table below lists the different contracts and the endpoints they define:

| Endpoint                           | Contract file                 |
|------------------------------------|-------------------------------|
| POST &ensp; /books                 | shouldCreateBook.groovy      |
| GET &ensp;&nbsp;&nbsp; /books/{id} | shouldFindBookById.groovy    |
| PUT &ensp;&nbsp;&nbsp; /books/{id} | shouldUpdateBook.groovy      |

Both the API provider and consumer (including tests) are implemented Java. A list comprising the used frameworks/technologies is given below:

```
  Java                      11
  Gradle                    6.2.2
  Spring Boot               2.2.5.RELEASE
  Spring Cloud Contract     2.2.2.RELEASE
```

### Building & Running the tests

The complete project can be built with the following command executed at top level:

```
./gradlew clean build
```

The server tests will be generated from the contracts located in `cloud-contract-producer/src/test/resources/contracts/booksapi`. The Gradle task
`check` will generate and run the generated server tests (executed in the `cloud-contract-producer` directory):

```
./gradlew check
```

In order to be able to write the tests for the client side (API consumer) we need a stub implementation of the server contracts. The Gradle
task `verifierStubsJar` generates the client stubs and packages them in a JAR file. The JAR file can be published to local Maven repository with the
following task (executed in the `cloud-contract-producer` directory):

```
./gradlew publishStubsPublicationToMavenLocal
```

This will generate and publish the `cloud-contract-producer-0.0.1-SNAPSHOT-stubs.jar` JAR file to the local Maven repository. On the consumer side we
use the `AutoConfigureStubRunner` annotation to run the server stubs. Here in the `id` attribute we specify the coordinates of the previously
generated artifact and the port on which the server stubs will run. Additionally, we specify the location of the generated stubs, which in our case
is `LOCAL` (the stubs will be fetched from a local Maven repository):

```
@AutoConfigureStubRunner(ids = "com.bzb.spring.cloud:cloud-contract-producer:0.0.1-SNAPSHOT:stubs:8001", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
```

Finally, we can execute the client tests with the command executed in the `cloud-contract-consumer` directory:

```
./gradlew clean check
```
