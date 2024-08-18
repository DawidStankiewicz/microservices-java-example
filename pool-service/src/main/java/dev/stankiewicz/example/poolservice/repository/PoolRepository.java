package dev.stankiewicz.example.poolservice.repository;

import dev.stankiewicz.example.poolservice.model.Pool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "pools", path = "pools")
public interface PoolRepository extends JpaRepository<Pool, Long> {
}
