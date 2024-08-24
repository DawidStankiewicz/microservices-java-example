# Work in progress 🔜

# microservices java example

## Features demonstrated

#### ✅ Microservice in Spring Boot with REST API
- pool-service with PoolRepository created using `@RepositoryRestResource`

#### ✅ DB service
- mongodb

#### ✅ Centralized & boot time loaded service configuration
- Spring Cloud configuration server with git-based backend
- **pool-service** connects to **configuration-server** on boot time and loads available configuration based on `spring.profiles.active` property (e.g. `java -Dspring.profiles.active=dev -jar target\pool-service-0.0.1-SNAPSHOT.jar`)

#### ✅ Version controlled configuration 
- configuration-server looks for configuration on git (at [configuration](/configuration) directory)

#### 🔜 Microservices can refresh when a new configuration appears
- while using `@RefreshScope` annotation, Spring Boot Actuator adds `/refresh` endpoint to refresh the service and reread the configuration form **configuration-server**

#### 🔜 Secrets in microservice's configuration are encrypted (like password to database)

#### ✅ Auto discovery of microservices
- `pool-service` and `result-service` register themselves in discovery service

#### ✅ Feign Client with HATEOAS example
- check `PoolVotesFeignClient`
- example of reading collection from _embedded HAT+JSON using `CollectionModel`

#### 🔜 Microservices resilience patterns
Check current state at `/actuator/health`

- 🔜 Retry
- 🔜 Circuit Breaker
  - Check `PoolVotesFeignClient` in **results-service**
- 🔜 Rate Limiter
- 🔜 Time Limiter
- 🔜 Bulkhead
- 🔜 Cache
- 🔜 Fallback

#### 🔜 Deployment using Docker containers

#### 🔜 Automatic service replication

#### ✅ Automatic health-check endpoint using Spring Actuator
- **pool-service** has in its `pom.xml` `spring-boot-starter-actuator` dependency, so **pool-service** status can be retrieved from `/actuator/health`

#### 🔜 Automatic health checks

#### 🔜 Service monitoring

#### 🔜 Gateway service

#### 🔜 OAuth2

#### 🔜 JWT token

#### 🔜 Messaging system - Spring Cloud Stream + Kafka

#### 🔜 Deployment on AWS services

#### 🔜 CI/CD pipelines for each module

#### 🔜 Unit tests job

#### 🔜 Integration tests job

#### 🔜 Coverage job

#### 🔜 Build job + artifactory

#### 🔜 Data caching

#### 🔜 Logging aggregation