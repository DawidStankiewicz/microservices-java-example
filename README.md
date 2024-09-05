# Work in progress 🔜

# microservices java example

## Features demonstrated

#### ✅ Microservice in Spring Boot with REST API
- pool-service with PoolRepository created using `@RepositoryRestResource`

#### ✅ DB service
- postgres

#### ✅ Centralized & boot time loaded service configuration
- Spring Cloud configuration server with git-based backend
- **pool-service** connects to **configuration-server** on boot time and loads available configuration based on `spring.profiles.active` property (e.g. `java -Dspring.profiles.active=dev -jar target\pool-service-0.0.1-SNAPSHOT.jar`)

#### ✅ Version controlled configuration 
- configuration-server looks for configuration on git (at [configuration](/configuration) directory)

#### ✅ Microservices can refresh when a new configuration appears
- while using `@RefreshScope` annotation, Spring Boot Actuator adds `/refresh` endpoint to refresh the service and reread the configuration form **configuration-server**
- applied in **pool-service** and **results-service**

#### 🔜 Secrets in microservice's configuration are encrypted (like password to database)

#### ✅ Auto discovery of microservices
- `pool-service` and `result-service` register themselves in discovery service

#### ✅ Feign Client with HATEOAS example
- check `PoolVotesFeignClient`
- example of reading collection from _embedded HAT+JSON using `CollectionModel`

#### ✅ Microservices resilience patterns
Check current state at `/actuator/health`.  
Patterns are configured in **results-service**.

- ✅ Retry
- ✅ Circuit Breaker
- ✅ Rate Limiter
- ✅ Bulkhead
- ✅ Fallback
- 🔜 Cache

#### ✅ Deployment using Docker containers
- ✅ Docker Compose 
- 🔜 Kubernetes

#### 🔜 Automatic service replication

#### ✅ Automatic health-check endpoint using Spring Actuator
- **pool-service** has in its `pom.xml` `spring-boot-starter-actuator` dependency, so **pool-service** status can be retrieved from `/actuator/health`

#### ✅ Automatic health checks
- ✅ Docker Compose health checks
- 🔜 Kubernetes health checks

#### 🔜 Service monitoring

#### ✅ Gateway service
- **gateway-server** as reverse proxy for all services registered in **discovery-service**

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

## Start solution

Run the whole solution on localhost using single script:

```bash
bash build_and_start.sh
```

if you have already built services' jars, then you can skip build part and just start docker compose:

```bash
bash start.sh
```