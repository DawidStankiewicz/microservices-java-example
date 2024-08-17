package dev.stankiewicz.example.poolservice.repository;

import dev.stankiewicz.example.poolservice.model.Pool;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "pools", path = "pools")
public interface PoolRepository extends MongoRepository<Pool, String> {
}
